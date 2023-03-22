package com.rongxin.mobile.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.core.domain.model.LoginBody;
import com.rongxin.common.core.domain.model.LoginUser;
import com.rongxin.common.core.domain.model.RegisterBody;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.StringUtils;
import com.rongxin.mobile.tencent.GetOpenIdUtil;
import com.rongxin.mobile.tencent.GetWxInfo;
import com.rongxin.mobile.tencent.WxVo;
import com.rongxin.module.sms.aliyun.service.AliYunSmsService;
import com.rongxin.web.framework.web.service.MobileLoginService;
import com.rongxin.web.framework.web.service.TokenService;
import com.rongxin.web.service.ISysUserService;
import com.rongxin.web.service.impl.SysUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信登录控制器
 *
 * @author rx
 * @since 2023/3/23
 */
@Api(tags = "[微信接口]")
@RestController
@RequestMapping("/wx/account")
public class WxLoginController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(WxLoginController.class);

    @Autowired(required = false)
    private MobileLoginService loginService;
    @Autowired
    private ISysUserService userService;

    @Value("${wx.host}")
    private String wxHost;

    @ApiOperation("getOpenToken")
    @GetMapping("/getOpenToken")
    @ResponseBody
    public AjaxResult getOpenToken() {
        return  AjaxResult.success(GetOpenIdUtil.getOpenToken(wxHost));
    }
    @ApiOperation("getOpenPhone")
    @PostMapping("/getOpenPhone")
    @ResponseBody
    public AjaxResult getOpenPhone(@RequestBody Map<String,Object> map) {
        if(map.get("code")== null ){
            return AjaxResult.error("微信code获取失败");
        }
        String jsonPhoneStr =  GetOpenIdUtil.getOpenPhone(wxHost,String.valueOf(map.get("access_token")),String.valueOf(map.get("code")));

        return  AjaxResult.success(jsonPhoneStr);
    }
    /**
     * 根据code获取微信登录手机号码
     */
    @ApiOperation("根据code获取微信登录手机号码")
    @PostMapping("/getWxPhoneData")
    @ResponseBody
    public AjaxResult getWxPhoneData(@RequestBody Map<String,Object> map) {
        if(map.get("code")== null ){
            return AjaxResult.error("微信code获取失败");
        }
        if(map.get("areaCode")== null ){
            return AjaxResult.error("地理信息获取失败");
        }
        String code = String.valueOf(map.get("code"));
        String areaCode = String.valueOf(map.get("areaCode"));

        String jsonTokenStr =  GetOpenIdUtil.getOpenToken(wxHost);
        Map tokenMap = JSONObject.parseObject(jsonTokenStr, Map.class);
        String jsonPhoneStr =  GetOpenIdUtil.getOpenPhone(wxHost,String.valueOf(tokenMap.get("access_token")),code);
        Map phoneMap = JSONObject.parseObject(jsonPhoneStr, Map.class);

        String phone_info = String.valueOf(phoneMap.get("phone_info"));
        Map phoneData = JSONObject.parseObject(phone_info, Map.class);
        String phone = String.valueOf(phoneData.get("phoneNumber"));

        return AjaxResult.success(phone);

    }
    /**
     * 根据code获取微信登录信息
     */
    @ApiOperation("微信登录获取OPENID信息")
    @PostMapping("/getWxOpenId")
    @ResponseBody
    public AjaxResult getWxOpenId(@RequestBody WxVo wxVo) {
        String jsonStr =  GetOpenIdUtil.getOpenId(wxHost,wxVo.getCode());
        return AjaxResult.success(JSONObject.parseObject(jsonStr, Map.class));
    }
    /**
     * 微信OPENID登录
     */
    @ApiOperation("微信OPENID登录")
    @PostMapping("/wxOpenIdLogin")
    @ResponseBody
    public AjaxResult wxOpenIdLogin(@RequestBody WxVo wxVo) {
        String jsonStr =  GetOpenIdUtil.getOpenId(wxHost,wxVo.getCode());
        Map map = JSONObject.parseObject(jsonStr, Map.class);
        String openID = String.valueOf(map.get("openid"));
        SysUser sysUser = userService.selectUserByAuthId(openID);
        LoginParams loginParams = new LoginParams();

        if(sysUser == null){
            RegisterBody user = new RegisterBody();
            user.setAuthId(openID);
            user.setUsername(wxVo.getUserName());
            user.setNickName(wxVo.getUserName());
            loginService.wxRegisterUser(user);
        }
        loginParams.setAuthId(openID);
        loginParams.setLoginType("3");
        return AjaxResult.success(loginService.wxOpenIdlogin(loginParams));
    }
    /**
     * 解析微信UserInfo
     */
    @ApiOperation("解析微信UserInfo")
    @PostMapping("/getWxUserInfo")
    @ResponseBody
    public AjaxResult getWxUserInfo(@RequestBody WxVo wxVo) {
        return AjaxResult.success(GetWxInfo.getUserInfo(wxVo.getEncryptedData(),wxVo.getSessionKey(),wxVo.getIv()));
    }

}
