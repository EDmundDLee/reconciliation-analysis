package com.rongxin.cms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizRuleMapper;
import com.rongxin.cms.domain.BizRule;
import com.rongxin.cms.service.IBizRuleService;

/**
 * 内容拓展属性规则表Service业务层处理
 * 
 * @author rx
 * @date 2022-11-01
 */
@Service
public class BizRuleServiceImpl extends ServiceImpl<BizRuleMapper, BizRule> implements IBizRuleService
{
    @Autowired
    private BizRuleMapper bizRuleMapper;

    /**
     * 查询内容拓展属性规则表
     * 
     * @param id 内容拓展属性规则表主键
     * @return 内容拓展属性规则表
     */
    @Override
    public BizRule selectBizRuleById(Long id)
    {
        return bizRuleMapper.selectBizRuleById(id);
    }

    /**
     * 查询内容拓展属性规则表列表
     * 
     * @param bizRule 内容拓展属性规则表
     * @return 内容拓展属性规则表
     */
    @Override
    public List<BizRule> selectBizRuleList(BizRule bizRule)
    {
        return bizRuleMapper.selectBizRuleList(bizRule);
    }

    /**
     * 新增内容拓展属性规则表
     * 
     * @param bizRule 内容拓展属性规则表
     * @return 结果
     */
    @Override
    public int insertBizRule(BizRule bizRule)
    {
        return bizRuleMapper.insertBizRule(bizRule);
    }

    /**
     * 修改内容拓展属性规则表
     * 
     * @param bizRule 内容拓展属性规则表
     * @return 结果
     */
    @Override
    public int updateBizRule(BizRule bizRule)
    {
        return bizRuleMapper.updateBizRule(bizRule);
    }

    /**
     * 批量删除内容拓展属性规则表
     * 
     * @param ids 需要删除的内容拓展属性规则表主键
     * @return 结果
     */
    @Override
    public int deleteBizRuleByIds(Long[] ids)
    {
        return bizRuleMapper.deleteBizRuleByIds(ids);
    }

    /**
     * 删除内容拓展属性规则表信息
     * 
     * @param id 内容拓展属性规则表主键
     * @return 结果
     */
    @Override
    public int deleteBizRuleById(Long id)
    {
        return bizRuleMapper.deleteBizRuleById(id);
    }
}
