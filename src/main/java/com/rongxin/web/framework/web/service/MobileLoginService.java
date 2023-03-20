package com.rongxin.web.framework.web.service;


import com.rongxin.common.constant.Constants;
import com.rongxin.common.constant.UserConstants;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.core.domain.model.LoginUser;
import com.rongxin.common.core.domain.model.RegisterBody;
import com.rongxin.common.core.redis.RedisCache;
import com.rongxin.common.exception.ServiceException;
import com.rongxin.common.exception.user.UserPasswordNotMatchException;
import com.rongxin.common.utils.DictUtils;
import com.rongxin.common.utils.MessageUtils;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.StringUtils;
import com.rongxin.mobile.controller.LoginParams;
import com.rongxin.module.sms.aliyun.properties.ConstantsSms;
import com.rongxin.module.sms.aliyun.service.AliYunSmsService;
import com.rongxin.web.framework.manager.AsyncManager;
import com.rongxin.web.framework.manager.factory.AsyncFactory;
import com.rongxin.web.service.ISysRoleService;
import com.rongxin.web.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * 移动端登录服务接口
 */
@Component
public class MobileLoginService  {

    private static final Logger log = LoggerFactory.getLogger(MobileLoginService.class);
    @Autowired
    private ISysUserService userService;
    @Autowired
    private AliYunSmsService aliYunSmsService;

    @Autowired(required = false)
    private TokenService tokenService;

    @Autowired(required = false)
    private SysLoginService sysLoginService;

    @Autowired(required = false)
    private ISysRoleService sysRoleService;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;



    /**
     * 注入redis服务
     */
    @Autowired(required = false)
    private RedisCache redisCache;

    /**
     * 请求时间戳过期时间5分钟
     */
    private static final int REQUEST_TIME_OUT = 1000 * 60 * 5;


    /**
     * jwt密钥
     */
    @Value("${token.secret}")
    private String jwtSecretKey;

    public AjaxResult login(LoginParams loginParams) {
        log.debug("login and loginParams:{}", loginParams);

        if (Objects.isNull(loginParams)) {
            return AjaxResult.error(-6,"登录参数不能为空");
        }
        String loginType = loginParams.getLoginType();
        if(StringUtils.isBlank(loginType)){
            return AjaxResult.error(-6,"登录方式不能为空");
        }
        //登录方式0验证码登录，1用户名密码登录，2本机一键登录，3微信单点登录
        if(loginType.equals("0")){
            String phoneNo = loginParams.getPhoneNo();
            if(StringUtils.isBlank(phoneNo)){
                return AjaxResult.error(-6,"登录名不能为空");
            }
            String validCode = loginParams.getValidCode();
            //2表示登录验证码，校验验证码合法性
            //aliYunSmsService.checkVerifyCode(phoneNo,validCode);
            loginParams.setUsername(phoneNo);
            loginParams.setPassword("SSO_LOGIN");
        }else if(loginType.equals("1")){
            String password = loginParams.getPassword();
            if(StringUtils.isBlank(password)){
                return AjaxResult.error(-6,"密码不能为空");
            }
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginParams.getUsername(), loginParams.getPassword()));

            //微信登录
//            if("3".equals(loginParams.getLoginType()))
//            {
//                authentication = authenticationManager.authenticate(new WxCodeAuthenticationToken(loginParams.getUsername()));
//            }
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginParams.getUsername(), Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginParams.getUsername(), Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //loginUser.setSource("app");
        SysUser user = loginUser.getUser();
        // 生成token
        String token = tokenService.createToken(loginUser);
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginParams.getUsername(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));

        sysLoginService.recordLoginInfo(user.getUserId());
        //判断用户是否存在管理员角色
        // 角色集合
//        Set<String> roles = permissionService.getRolePermission(user);
//        boolean roleFlag = false;
//        if(!CollectionUtils.isEmpty(roles)){
//            for (String roleKey : roles) {
//                if(roleKey.equals("agent")){
//                    roleFlag = true;
//                    break;
//                }
//            }
//        }
        AjaxResult ajax = AjaxResult.success("");
        ajax.put("token",token);
        //token过期时间
        ajax.put("expired",loginUser.getExpireTime());
        ajax.put("user",loginUser.getUser());
        ajax.put("isAgent",String.valueOf(true));
        return ajax;
    }

    /**
     * 发送注册验证码
     * @param loginParams
     * @return
     */
    public AjaxResult sendCode(LoginParams loginParams) {
        boolean returnFlag = true;
        if (Objects.isNull(loginParams)) {
            return AjaxResult.error(-6,"参数为空");
        }
        // 验证验证码
        if (StringUtils.isBlank(loginParams.getPhoneNo())) {
            return AjaxResult.error(-6,"发送手机号不能为空");
        }
        String validCodeType = "2";
        if (StringUtils.isNotBlank(loginParams.getValidCodeType())) {
            validCodeType = loginParams.getValidCodeType();
        }
        try{
            String randCode = aliYunSmsService.getRandCode(6);
            String parm = "{\"code\":" + randCode + "}";
            returnFlag =aliYunSmsService.sendSms(loginParams.getPhoneNo(), ConstantsSms.signName, ConstantsSms.templateCodeA, parm,true);
            if(!returnFlag){
                return AjaxResult.error(-6,"对不起手机号【"+loginParams.getPhoneNo()+"】发送短信失败：失败原因:");
            }
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
        AjaxResult ajax = AjaxResult.success("验证码发送成功");
        return ajax;
    }

    /**
     * 注册
     */
    public String registerUser(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            SysUser sysUser = new SysUser();
            sysUser.setUserName(username);
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER,
                        MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 设置注册用户角色部门岗位信息
     * @param registerUser
     * @return
     */
    private void setUserDefaultInfo(SysUser registerUser ){
        String registerRoleCode = DictUtils.getDictValue("sys_config","register_role_code","");
        if (StringUtils.isBlank(registerRoleCode)) {
            throw new ServiceException("请前往数据字典【sys_config】中维护注册用户角色编码【register_role_code】");
        }
        String registerDeptCode = DictUtils.getDictValue("sys_config","register_dept_code","");
        if (StringUtils.isBlank(registerDeptCode)) {
            throw new ServiceException("请前往数据字典【sys_config】中维护注册用户部门编码【register_dept_code】");
        }
        String registerPostCode = DictUtils.getDictValue("sys_config","register_post_code","");
        if (StringUtils.isBlank(registerPostCode)) {
            throw new ServiceException("请前往数据字典【sys_config】中维护注册用户岗位编码【register_post_code】");
        }

    }

}
