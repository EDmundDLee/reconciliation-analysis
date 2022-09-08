package com.rongxin.demo.controller;

import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.demo.service.ApplyService;
import com.rongxin.framework.websocket.WebSocketServer;
import com.rongxin.web.framework.web.service.impl.UserDetailsServiceImpl;
import com.rongxin.wechatPay.bo.PayBo;
import com.rongxin.wechatPay.errors.BusinessException;
import com.rongxin.wechatPay.vo.PayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    private static final Logger log = LoggerFactory.getLogger(ApplyController.class);

    @Resource
    ApplyService applyService;

    @Autowired
    private WebSocketServer webSocketServer;

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
    public AjaxResult weChatPayCallBack(HttpServletRequest request) throws IOException {
        log.info("微信付款回调开始---------------------------------");
        String result = applyService.weChatPayCallBack(request);
        log.info("微信付款回调结束---------------------------------=｛｝", result);
        //前端发送消息
        String userName = SecurityUtils.getUsername();
        webSocketServer.sendInfo("有新消息!", userName);
        return AjaxResult.success(result);
    }

}
