package com.rongxin.demo.service;


import com.rongxin.wechatPay.bo.PayBo;
import com.rongxin.wechatPay.vo.PayVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liulu
 * @version 1.0
 * @date 2022/4/18 14:44
 */
public interface ApplyService {


    PayVo pay(PayBo bo);

    String weChatPayCallBack(HttpServletRequest request);
}
