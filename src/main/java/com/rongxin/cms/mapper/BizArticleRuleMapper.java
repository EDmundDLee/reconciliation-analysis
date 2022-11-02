package com.rongxin.cms.mapper;

import java.util.List;
import com.rongxin.cms.domain.BizArticleRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 规则内容关系Mapper接口
 * 
 * @author rx
 * @date 2022-11-01
 */
public interface BizArticleRuleMapper  extends BaseMapper<BizArticleRule>
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
     * 删除规则内容关系
     * 
     * @param id 规则内容关系主键
     * @return 结果
     */
    public int deleteBizArticleRuleById(Long id);

    /**
     * 批量删除规则内容关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizArticleRuleByIds(Long[] ids);


    public int deleteBizArticleRuleByRuleId(String ruleId);
}
