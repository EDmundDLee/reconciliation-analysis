package com.rongxin.cms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizArticleRuleMapper;
import com.rongxin.cms.domain.BizArticleRule;
import com.rongxin.cms.service.IBizArticleRuleService;

/**
 * 规则内容关系Service业务层处理
 * 
 * @author rx
 * @date 2022-11-01
 */
@Service
public class BizArticleRuleServiceImpl extends ServiceImpl<BizArticleRuleMapper, BizArticleRule> implements IBizArticleRuleService
{
    @Autowired
    private BizArticleRuleMapper bizArticleRuleMapper;

    /**
     * 查询规则内容关系
     * 
     * @param id 规则内容关系主键
     * @return 规则内容关系
     */
    @Override
    public BizArticleRule selectBizArticleRuleById(Long id)
    {
        return bizArticleRuleMapper.selectBizArticleRuleById(id);
    }

    /**
     * 查询规则内容关系列表
     * 
     * @param bizArticleRule 规则内容关系
     * @return 规则内容关系
     */
    @Override
    public List<BizArticleRule> selectBizArticleRuleList(BizArticleRule bizArticleRule)
    {
        return bizArticleRuleMapper.selectBizArticleRuleList(bizArticleRule);
    }

    /**
     * 新增规则内容关系
     * 
     * @param bizArticleRule 规则内容关系
     * @return 结果
     */
    @Override
    public int insertBizArticleRule(BizArticleRule bizArticleRule)
    {
        return bizArticleRuleMapper.insertBizArticleRule(bizArticleRule);
    }

    /**
     * 修改规则内容关系
     * 
     * @param bizArticleRule 规则内容关系
     * @return 结果
     */
    @Override
    public int updateBizArticleRule(BizArticleRule bizArticleRule)
    {
        return bizArticleRuleMapper.updateBizArticleRule(bizArticleRule);
    }

    /**
     * 批量删除规则内容关系
     * 
     * @param ids 需要删除的规则内容关系主键
     * @return 结果
     */
    @Override
    public int deleteBizArticleRuleByIds(Long[] ids)
    {
        return bizArticleRuleMapper.deleteBizArticleRuleByIds(ids);
    }

    /**
     * 删除规则内容关系信息
     * 
     * @param id 规则内容关系主键
     * @return 结果
     */
    @Override
    public int deleteBizArticleRuleById(Long id)
    {
        return bizArticleRuleMapper.deleteBizArticleRuleById(id);
    }
}
