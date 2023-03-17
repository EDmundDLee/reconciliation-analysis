package com.rongxin.web.domain;

import com.rongxin.common.core.domain.BaseEntity;
import com.rongxin.common.core.domain.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 业务字典对象 business_dict
 * 
 * @author rx
 * @date 2023-03-14
 */
@ApiModel("业务字典实体")
public class BusinessDict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 字典类型 */
    @Excel(name = "字典类型")
    @ApiModelProperty("字典类型")
    private String type;

    /** 字典级别 */
    @Excel(name = "字典级别")
    @ApiModelProperty("字典级别")
    private String level;

    /** 字典项编码 */
    @Excel(name = "字典项编码")
    @ApiModelProperty("字典项编码")
    private String code;

    /** 字典项值 */
    @Excel(name = "字典项值")
    @ApiModelProperty("字典项值")
    private String value;

    /** 描述 */
    private String description;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty("排序")
    private Long sort;

    /** 是否可用 */
    @Excel(name = "是否可用")
    @ApiModelProperty("是否可用")
    private String isEnabled;
    @ApiModelProperty("父节点ID")
    private String parentId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsEnabled(String isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled()
    {
        return isEnabled;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("level", getLevel())
            .append("code", getCode())
            .append("value", getValue())
            .append("description", getDescription())
            .append("sort", getSort())
            .append("parentId", getParentId())
            .append("isEnabled", getIsEnabled())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
