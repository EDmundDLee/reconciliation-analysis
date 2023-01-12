package com.rongxin.cms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 栏目类别对象 biz_column
 * 
 * @author rx
 * @date 2022-10-10
 */
public class BizColumn extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识 */
    private Long id;

    /** 栏目名称 */
    @Excel(name = "栏目名称")
    private String name;
    /** 栏目级别 */
    @Excel(name = "栏目级别")
    private String level;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 创建人名称 */
    @Excel(name = "创建人名称")
    private String createName;
    private String ruleName;
    private String ruleStatus;


    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createId;

    /** 逻辑删除:1删除 0:未删除 */
    @Excel(name = "逻辑删除:1删除 0:未删除")
    private Integer colDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setCreateName(String createName) 
    {
        this.createName = createName;
    }

    public String getCreateName() 
    {
        return createName;
    }
    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }
    public void setColDelete(Integer colDelete) 
    {
        this.colDelete = colDelete;
    }

    public Integer getColDelete() 
    {
        return colDelete;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("name", getName())
            .append("level", getLevel())
            .append("createDate", getCreateDate())
            .append("createName", getCreateName())
            .append("createId", getCreateId())
            .append("colDelete", getColDelete())
            .append("ruleName", getRuleName())
            .append("ruleStatus", getRuleStatus())
            .toString();
    }
}
