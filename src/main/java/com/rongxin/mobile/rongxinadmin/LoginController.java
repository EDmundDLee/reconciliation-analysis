package com.rongxin.mobile.rongxinadmin;

import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.core.domain.model.LoginUser;
import com.rongxin.common.core.domain.model.RegisterBody;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.StringUtils;
import com.rongxin.module.sms.aliyun.service.AliYunSmsService;
import com.rongxin.web.framework.web.service.MobileLoginService;
import com.rongxin.web.framework.web.service.TokenService;
import com.rongxin.web.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 *
 * @author SK
 * @since 2018/6/13
 */
@RestController
@RequestMapping("/mobile/login")
public class LoginController extends BaseController {

    @Autowired(required = false)
    private TokenService tokenService;

    @Autowired(required = false)
    private MobileLoginService loginService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private AliYunSmsService aliYunSmsService;


    /**
     * 手机登录
     * @return -1 用户名或密码错误  -2 账号冻结  -3 账号锁定 1 成功  -4 验证码错误
     */
    @Log(title = "手机登录", businessType = BusinessType.GRANT)
    @PostMapping("/loginByPassword")
    @ResponseBody
    public AjaxResult loginByPassword(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo");
        String validCode = request.getParameter("validCode");
        String loginType = request.getParameter("loginType");
        // 登录结果
        LoginParams loginParams = new LoginParams();
        loginParams.setUsername(username);
        loginParams.setPassword(password);
        loginParams.setPhoneNo(phoneNo);
        loginParams.setValidCode(validCode);
        loginParams.setLoginType(loginType);
        return loginService.login(loginParams);
    }
    /**
     * 重置密码
     */
    @Log(title = "手机重置密码", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(HttpServletRequest request)
    {
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phoneNo");
        SysUser user = userService.selectUserByUserName(phoneNo);
        if(user!=null){
            user.setUserName(phoneNo);
            user.setPassword(password);
            userService.checkUserAllowed(user);
            userService.checkUserDataScope(user.getUserId());
            user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
            user.setUpdateBy(getUsername());
            return toAjax(userService.resetPwd(user));
        }else{
            return toAjax(false);
        }

    }
    /**
     * 发送验证码
     */
    @Log(title = "手机发送验证码", businessType = BusinessType.UPDATE)
    @PostMapping("/sendCode")
    @ResponseBody
    public AjaxResult sendRegisterCode(HttpServletRequest request) {
        String phoneNo = request.getParameter("phoneNo");
        String validCodeType = request.getParameter("validCodeType");
        // 登录结果
        LoginParams loginParams = new LoginParams();
        loginParams.setPhoneNo(phoneNo);
        loginParams.setValidCodeType(validCodeType);
        return loginService.sendCode(loginParams);
    }
    /**
     * 验证验证码
     */
    @Log(title = "验证验证码", businessType = BusinessType.UPDATE)
    @PostMapping("/checkCode")
    @ResponseBody
    public AjaxResult checkCode(HttpServletRequest request) {
        String phoneNo = request.getParameter("phoneNo");
        String validCode = request.getParameter("validCode");
        if(aliYunSmsService.checkVerifyCode(phoneNo,validCode)){
            return AjaxResult.success("验证成功");
        }else{
            return AjaxResult.success("验证失败");
        }
    }
    @Log(title = "手机退出登录", businessType = BusinessType.UPDATE)
    @GetMapping("/logout")
    @ResponseBody
    public AjaxResult logout(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if(null != loginUser){
            tokenService.delLoginUser(loginUser.getToken());
        }
        return AjaxResult.success("退出成功！");
    }

    /**
     * 注册用户
     *
     * @return String
     */
    @PostMapping("/registerUser")
    @ResponseBody
    public AjaxResult registerUser(HttpServletRequest request) {
        String phoneNo = request.getParameter("phoneNo");
        String password = request.getParameter("pasword");
        if (StringUtils.isBlank(phoneNo)) {
            return AjaxResult.error(-6,"发送手机号不能为空");
        }
        if (StringUtils.isBlank(phoneNo)) {
            return AjaxResult.error(-6,"发送手机号不能为空");
        }
        // 登录结果
        RegisterBody user = new RegisterBody();
        user.setUsername(phoneNo);
        user.setPassword(password);
        return AjaxResult.success(loginService.registerUser(user));
    }

}
