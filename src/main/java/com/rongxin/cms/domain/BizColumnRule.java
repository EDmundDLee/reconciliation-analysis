package com.rongxin.cms.domain;

import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 栏目规则属性对象 biz_column_rule
 * 
 * @author rx
 * @date 2022-11-08
 */
public class BizColumnRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 所属栏目 */
    @Excel(name = "所属栏目")
    private Long columnId;

    /** 所属规则id */
    @Excel(name = "所属规则id")
    private Long ruleId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setColumnId(Long columnId) 
    {
        this.columnId = columnId;
    }

    public Long getColumnId() 
    {
        return columnId;
    }
    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("columnId", getColumnId())
            .append("ruleId", getRuleId())
            .toString();
    }
}
