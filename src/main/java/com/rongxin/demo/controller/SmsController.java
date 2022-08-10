package com.rongxin.demo.controller;


import com.rongxin.module.sms.aliyun.properties.ConstantsSms;
import com.rongxin.module.sms.aliyun.service.AliYunSmsService;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.enums.BusinessType;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class SmsController {

    @Autowired
    private AliYunSmsService aliYunSmsService;

    @ApiOperation("发送短信")
    @Log(title = "示例功能", businessType = BusinessType.DELETE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "smsCode", value = "短信编码", paramType = "query", required = true, dataType="String"),
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", required = true, dataType="String"),
            @ApiImplicitParam(name = "params", value = "参数", paramType = "query", required = true, dataType="String")
    })
    @PostMapping("send")
    public AjaxResult send(String smsCode, String mobile, String params) throws Exception{
        String randCode = aliYunSmsService.getRandCode(6);
        String parm = "{\"code\":" + randCode + "}";
        aliYunSmsService.sendSms("手机号", ConstantsSms.signName, ConstantsSms.templateCodeA, parm,true);
        return AjaxResult.success();
    }
}
