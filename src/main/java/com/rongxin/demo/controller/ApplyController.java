package com.rongxin.demo.controller;

import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.demo.service.ApplyService;
import com.rongxin.wechatPay.bo.PayBo;
import com.rongxin.wechatPay.errors.BusinessException;
import com.rongxin.wechatPay.vo.PayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author liulu
 * @version 1.0
 * @date 2022/4/18 14:38
 */

@Slf4j
@RestController
@Api(tags = "评估申请")
@RequestMapping("/apply/test")
public class ApplyController {

    @Resource
    ApplyService applyService;


    @PreAuthorize("@ss.hasPermi('apply:test:pay')")
    @GetMapping(value = "/pay")
    @ApiOperation("测试付款")
    public AjaxResult pay(PayBo bo)
            throws BusinessException {
        PayVo vo = applyService.pay(bo);
        return AjaxResult.success(vo);
    }

    @ApiOperation("微信付款回调")
//    @RequiredPermission(value = "pass")
    @RequestMapping(value = "/weChatCallBack")
    public AjaxResult weChatPayCallBack(HttpServletRequest request) {
        log.info("微信付款回调开始---------------------------------");
        String result = applyService.weChatPayCallBack(request);
        log.info("微信付款回调结束---------------------------------=｛｝", result);
        return AjaxResult.success(result);
    }

}
