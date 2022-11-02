package com.rongxin.cms.domain;

import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 内容拓展属性值对象 biz_attribute_value
 * 
 * @author rx
 * @date 2022-11-01
 */
public class BizAttributeValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 所属规则 */
    @Excel(name = "所属规则")
    private Long ruleId;

    /** 所属内容 */
    @Excel(name = "所属内容")
    private Long articleId;

    /** 值 */
    @Excel(name = "值")
    private String attrValue;

    /** 状态 */
    @Excel(name = "状态")
    private String attrStatus;

    /** 排序 */
    @Excel(name = "排序")
    private String attrOrder;

    /** 所属属性 */
    @Excel(name = "所属属性")
    private Long attrId;

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
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setAttrValue(String attrValue) 
    {
        this.attrValue = attrValue;
    }

    public String getAttrValue() 
    {
        return attrValue;
    }
    public void setAttrStatus(String attrStatus) 
    {
        this.attrStatus = attrStatus;
    }

    public String getAttrStatus() 
    {
        return attrStatus;
    }
    public void setAttrOrder(String attrOrder) 
    {
        this.attrOrder = attrOrder;
    }

    public String getAttrOrder() 
    {
        return attrOrder;
    }
    public void setAttrId(Long attrId) 
    {
        this.attrId = attrId;
    }

    public Long getAttrId() 
    {
        return attrId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ruleId", getRuleId())
            .append("articleId", getArticleId())
            .append("attrValue", getAttrValue())
            .append("attrStatus", getAttrStatus())
            .append("attrOrder", getAttrOrder())
            .append("attrId", getAttrId())
            .toString();
    }
}
