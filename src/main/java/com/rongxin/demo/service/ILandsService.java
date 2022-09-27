package com.rongxin.demo.service;


import com.rongxin.eqb.res.EQianBaoBaseResForm;
import com.rongxin.wechatPay.errors.BusinessException;


/**
 * 用户土地Service
 *
 * @Date 2022-05-27
 */
public interface ILandsService {



    /**
     * 8.【咨询申请】--生成合同
     *
     * @return 合同链接地址
     * @throws BusinessException 生成合同出现异常
     */
    EQianBaoBaseResForm generateTemplate() throws BusinessException;


}
