package com.rongxin.cms.mapper;

import java.util.List;
import com.rongxin.cms.domain.BizColumnRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 栏目规则属性Mapper接口
 * 
 * @author rx
 * @date 2022-11-08
 */
public interface BizColumnRuleMapper  extends BaseMapper<BizColumnRule>
{
    /**
     * 查询栏目规则属性
     * 
     * @param id 栏目规则属性主键
     * @return 栏目规则属性
     */
    public BizColumnRule selectBizColumnRuleById(Long id);

    /**
     * 查询栏目规则属性列表
     * 
     * @param bizColumnRule 栏目规则属性
     * @return 栏目规则属性集合
     */
    public List<BizColumnRule> selectBizColumnRuleList(BizColumnRule bizColumnRule);

    /**
     * 新增栏目规则属性
     * 
     * @param bizColumnRule 栏目规则属性
     * @return 结果
     */
    public int insertBizColumnRule(BizColumnRule bizColumnRule);

    /**
     * 修改栏目规则属性
     * 
     * @param bizColumnRule 栏目规则属性
     * @return 结果
     */
    public int updateBizColumnRule(BizColumnRule bizColumnRule);

    /**
     * 删除栏目规则属性
     * 
     * @param id 栏目规则属性主键
     * @return 结果
     */
    public int deleteBizColumnRuleById(Long id);

    /**
     * 批量删除栏目规则属性
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizColumnRuleByIds(Long[] ids);

    public int deleteBizArticleRuleByArticleIds(List<String> ids);
}
