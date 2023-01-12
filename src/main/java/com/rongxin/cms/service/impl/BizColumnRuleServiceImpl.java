package com.rongxin.cms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizColumnRuleMapper;
import com.rongxin.cms.domain.BizColumnRule;
import com.rongxin.cms.service.IBizColumnRuleService;

/**
 * 栏目规则属性Service业务层处理
 * 
 * @author rx
 * @date 2022-11-08
 */
@Service
public class BizColumnRuleServiceImpl extends ServiceImpl<BizColumnRuleMapper, BizColumnRule> implements IBizColumnRuleService
{
    @Autowired
    private BizColumnRuleMapper bizColumnRuleMapper;

    /**
     * 查询栏目规则属性
     * 
     * @param id 栏目规则属性主键
     * @return 栏目规则属性
     */
    @Override
    public BizColumnRule selectBizColumnRuleById(Long id)
    {
        return bizColumnRuleMapper.selectBizColumnRuleById(id);
    }

    /**
     * 查询栏目规则属性列表
     * 
     * @param bizColumnRule 栏目规则属性
     * @return 栏目规则属性
     */
    @Override
    public List<BizColumnRule> selectBizColumnRuleList(BizColumnRule bizColumnRule)
    {
        return bizColumnRuleMapper.selectBizColumnRuleList(bizColumnRule);
    }

    /**
     * 新增栏目规则属性
     * 
     * @param bizColumnRule 栏目规则属性
     * @return 结果
     */
    @Override
    public int insertBizColumnRule(BizColumnRule bizColumnRule)
    {
        return bizColumnRuleMapper.insertBizColumnRule(bizColumnRule);
    }

    /**
     * 修改栏目规则属性
     * 
     * @param bizColumnRule 栏目规则属性
     * @return 结果
     */
    @Override
    public int updateBizColumnRule(BizColumnRule bizColumnRule)
    {
        return bizColumnRuleMapper.updateBizColumnRule(bizColumnRule);
    }

    /**
     * 批量删除栏目规则属性
     * 
     * @param ids 需要删除的栏目规则属性主键
     * @return 结果
     */
    @Override
    public int deleteBizColumnRuleByIds(Long[] ids)
    {
        return bizColumnRuleMapper.deleteBizColumnRuleByIds(ids);
    }

    /**
     * 删除栏目规则属性信息
     * 
     * @param id 栏目规则属性主键
     * @return 结果
     */
    @Override
    public int deleteBizColumnRuleById(Long id)
    {
        return bizColumnRuleMapper.deleteBizColumnRuleById(id);
    }
}
