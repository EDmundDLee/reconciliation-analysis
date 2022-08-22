package com.rongxin.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.payment.bean.PaymentNotification;
import com.riversoft.weixin.pay.payment.bean.UnifiedOrderResponse;
import com.riversoft.weixin.pay.util.SignatureUtil;
import com.rongxin.demo.service.ApplyService;
import com.rongxin.demo.utils.SpringContextUtil;
import com.rongxin.wechatPay.bo.PayBo;
import com.rongxin.wechatPay.errors.BusinessException;
import com.rongxin.wechatPay.req.PayReqForm;
import com.rongxin.wechatPay.req.PaymentMethodEnum;
import com.rongxin.wechatPay.res.UnifiedOrderDefaultResponse;
import com.rongxin.wechatPay.service.IPayService;
import com.rongxin.wechatPay.vo.PayVo;
import com.rongxin.wechatPay.vo.VXPayCallBackResVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.SortedMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @author liulu
 * @version 1.0
 * @date 2022/4/18 14:45
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplyServiceImpl implements ApplyService {


    @Value("${vx.pay.call.back.url.suffix}")
    private String callBackUrl;

//    @Resource
//    private IPayService payService;

    @Value("${wechat.public.app.id}")
    private String wechatServiceId;

    @Value("${vx.pay.app.id}")
    private String wechatAppId;

    @Value("${vx.pay.mch.id}")
    private String wechatMchId;

    @Value("${vx.pay.sign.key}")
    private String wechatSignKey;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Resource
    SpringContextUtil springContextUtil;

    @SneakyThrows
    @Override
    public PayVo pay(PayBo bo1) {
        PayVo vo = new PayVo();
        String transactionNo ="1wertygfdsa1";
        vo.setTransactionNo(transactionNo);
        vo.setTransactionAmount(new BigDecimal("0.01"));
        String tradeNo = vo.getTransactionNo() + "-" + System.currentTimeMillis();
        PayReqForm payDTO = new PayReqForm();
        payDTO.setNeedCallBack(true);
        payDTO.setPayMethod(PaymentMethodEnum.WE_CHAT.getTypeCode());
        payDTO.setCallBackUrl(callBackUrl);
//        payDTO.setCallBackUrl(callBackUrl + "/api/v1.0/jnwq/apply/weChatCallBack");
        payDTO.setTradeNo(tradeNo);
        payDTO.setProductDesc("测试1");
        payDTO.setTransactionAmount(vo.getTransactionAmount().multiply(new BigDecimal(100)));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "1234567");
        jsonObject.put("transactionNo", transactionNo);
        jsonObject.put("tType", "评估申请");
        jsonObject.put("apply_id", "1qaz");
        payDTO.setAttach(jsonObject.toJSONString());
        //微信支付类型
        payDTO.setWeChatPayType("");
        IPayService payService = new IPayService();
        UnifiedOrderDefaultResponse response = payService.unifiedorder(payDTO, wechatAppId, wechatServiceId, wechatMchId, wechatSignKey, springContextUtil.getActiveProfile());
        UnifiedOrderResponse orderResponse = response.getOrderResponse();
        if (StringUtils.equals(orderResponse.getReturnCode(), "FAIL")) {
            throw new BusinessException(orderResponse.getReturnMessage());
        }
        log.info("微信付款二维码已生成 param = {}", JSONObject.toJSONString(payDTO));
        vo.setCodeUrl(orderResponse.getCodeUrl());
        vo.setPrepayId(orderResponse.getPrepayId());
        return vo;
    }

    @Override
    public String weChatPayCallBack(HttpServletRequest request) {
        InputStream inStream = null;
        String responseStr = "";
        try {
            inStream = request.getInputStream();
            responseStr = inputStreamToString(inStream);
            log.info("VX支付回调入参 : {}", responseStr);
            if (StringUtils.isEmpty(responseStr)) {
                return defaultFailString("参数异常");
            }
            lock.readLock().lock();
            PaymentNotification notification =
                    XmlObjectMapper.defaultMapper().fromXml(responseStr, PaymentNotification.class);
            String sign = notification.getSign();
            SortedMap<String, Object> resultMap = (SortedMap) JsonMapper.nonEmptyMapper().getMapper()
                    .convertValue(notification, SortedMap.class);
            resultMap.remove("sign");
            String generateSign = SignatureUtil.sign(resultMap, wechatSignKey);
            if (!StringUtils.equals(sign, generateSign)) {
                return defaultFailString("验签失败");
            }
            JSONObject jsonObject = JSONObject.parseObject(notification.getAttach());
            log.info("vx支付--------={}", jsonObject);
            String resultCode = notification.getResultCode();
            Long id = jsonObject.getLong("apply_id");
            payHandle(notification, jsonObject, id, resultCode);
            return defaultSuccessString();
        } catch (Exception e) {
            log.error("VX支付回调失败 responseStr :  {} , e : {}", responseStr, e);
            return null;
        } finally {
            lock.readLock().unlock();
            if (Objects.nonNull(inStream)) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        return "222";
    }

    private String inputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String oneLine = "";
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((oneLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(oneLine);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bufferedReader)) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(inputStreamReader)) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String defaultFailString(String reason) throws JsonProcessingException {
        VXPayCallBackResVo payCallBackResForm = new VXPayCallBackResVo();
        payCallBackResForm.setReturn_code("FAIL");
        payCallBackResForm.setReturn_msg(reason);
        return XmlObjectMapper.defaultMapper().toXml(payCallBackResForm);
    }

    private String defaultSuccessString() throws JsonProcessingException {
        VXPayCallBackResVo payCallBackResForm = new VXPayCallBackResVo();
        payCallBackResForm.setReturn_code("SUCCESS");
        return XmlObjectMapper.defaultMapper().toXml(payCallBackResForm);
    }

    private void payHandle(PaymentNotification notification, JSONObject jsonObject, Long id, String resultCode) {
        if (StringUtils.equals(resultCode, "SUCCESS")) {
//            insertPayment(notification, jsonObject, Boolean.TRUE, id);
            //记录写入付款信息
        } else {
            // 支付失败 不修改 项目状态 要让用户重新支付
//            insertPayment(notification, jsonObject, Boolean.FALSE, id);
        }
    }


}
