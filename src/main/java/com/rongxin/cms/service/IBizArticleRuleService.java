package com.rongxin.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizArticleRule;

/**
 * 规则内容关系Service接口
 * 
 * @author rx
 * @date 2022-11-01
 */
public interface IBizArticleRuleService extends IService<BizArticleRule>
{
    /**
     * 查询规则内容关系
     * 
     * @param id 规则内容关系主键
     * @return 规则内容关系
     */
    public BizArticleRule selectBizArticleRuleById(Long id);

    /**
     * 查询规则内容关系列表
     * 
     * @param bizArticleRule 规则内容关系
     * @return 规则内容关系集合
     */
    public List<BizArticleRule> selectBizArticleRuleList(BizArticleRule bizArticleRule);

    /**
     * 新增规则内容关系
     * 
     * @param bizArticleRule 规则内容关系
     * @return 结果
     */
    public int insertBizArticleRule(BizArticleRule bizArticleRule);

    /**
     * 修改规则内容关系
     * 
     * @param bizArticleRule 规则内容关系
     * @return 结果
     */
    public int updateBizArticleRule(BizArticleRule bizArticleRule);

    /**
     * 批量删除规则内容关系
     * 
     * @param ids 需要删除的规则内容关系主键集合
     * @return 结果
     */
    public int deleteBizArticleRuleByIds(Long[] ids);

    /**
     * 删除规则内容关系信息
     * 
     * @param id 规则内容关系主键
     * @return 结果
     */
    public int deleteBizArticleRuleById(Long id);
}
