package com.rongxin.cms.domain;

import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 内容拓展属性对象 biz_attribute
 * 
 * @author rx
 * @date 2022-11-01
 */
public class BizAttribute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 所属规则 */
    @Excel(name = "所属规则")
    private Long ruleId;

    /** 属性名称 */
    @Excel(name = "属性名称")
    private String attributeName;

    /** 属性状态 */
    @Excel(name = "属性状态")
    private String attributeStatus;

    /** 排序 */
    @Excel(name = "排序")
    private String attributeOrder;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }
    public void setAttributeName(String attributeName) 
    {
        this.attributeName = attributeName;
    }

    public String getAttributeName() 
    {
        return attributeName;
    }
    public void setAttributeStatus(String attributeStatus) 
    {
        this.attributeStatus = attributeStatus;
    }

    public String getAttributeStatus() 
    {
        return attributeStatus;
    }
    public void setAttributeOrder(String attributeOrder) 
    {
        this.attributeOrder = attributeOrder;
    }

    public String getAttributeOrder() 
    {
        return attributeOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ruleId", getRuleId())
            .append("attributeName", getAttributeName())
            .append("attributeStatus", getAttributeStatus())
            .append("attributeOrder", getAttributeOrder())
            .toString();
    }
}
