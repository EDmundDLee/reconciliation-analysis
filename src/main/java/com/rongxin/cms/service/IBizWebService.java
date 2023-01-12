package com.rongxin.cms.service;



import com.rongxin.cms.domain.BizArticle;
import java.util.List;
import java.util.Map;

/**
 * 文网站接口集合
 * 
 * @author rx
 * @date 2022-10-11
 */
public interface IBizWebService
{
    List<BizArticle> search(Map<String, Object> params) throws Exception;
    /* 同步es数据库 */
    void synchronizationArticle() throws Exception;
}
