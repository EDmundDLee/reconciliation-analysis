package com.rongxin.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizRule;

/**
 * 内容拓展属性规则表Service接口
 * 
 * @author rx
 * @date 2022-11-01
 */
public interface IBizRuleService extends IService<BizRule>
{
    /**
     * 查询内容拓展属性规则表
     * 
     * @param id 内容拓展属性规则表主键
     * @return 内容拓展属性规则表
     */
    public BizRule selectBizRuleById(Long id);

    /**
     * 查询内容拓展属性规则表列表
     * 
     * @param bizRule 内容拓展属性规则表
     * @return 内容拓展属性规则表集合
     */
    public List<BizRule> selectBizRuleList(BizRule bizRule);

    /**
     * 新增内容拓展属性规则表
     * 
     * @param bizRule 内容拓展属性规则表
     * @return 结果
     */
    public int insertBizRule(BizRule bizRule);

    /**
     * 修改内容拓展属性规则表
     * 
     * @param bizRule 内容拓展属性规则表
     * @return 结果
     */
    public int updateBizRule(BizRule bizRule);

    /**
     * 批量删除内容拓展属性规则表
     * 
     * @param ids 需要删除的内容拓展属性规则表主键集合
     * @return 结果
     */
    public int deleteBizRuleByIds(Long[] ids);

    /**
     * 删除内容拓展属性规则表信息
     * 
     * @param id 内容拓展属性规则表主键
     * @return 结果
     */
    public int deleteBizRuleById(Long id);
}
