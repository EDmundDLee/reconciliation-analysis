package com.rongxin.cms.domain;

import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 内容拓展属性规则表对象 biz_rule
 * 
 * @author rx
 * @date 2022-11-01
 */
public class BizRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 规则名称 */
    @Excel(name = "规则名称")
    private String ruleName;

    /** 状态 */
    @Excel(name = "状态")
    private String ruleStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRuleName(String ruleName) 
    {
        this.ruleName = ruleName;
    }

    public String getRuleName() 
    {
        return ruleName;
    }
    public void setRuleStatus(String ruleStatus) 
    {
        this.ruleStatus = ruleStatus;
    }

    public String getRuleStatus() 
    {
        return ruleStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ruleName", getRuleName())
            .append("ruleStatus", getRuleStatus())
            .toString();
    }
}
