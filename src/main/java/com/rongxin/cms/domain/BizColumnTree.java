package com.rongxin.cms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.annotation.Excel;
import com.rongxin.common.core.domain.BaseEntity;
import com.rongxin.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 栏目类别对象 biz_column
 * 
 * @author rx
 * @date 2022-10-10
 */
public class BizColumnTree extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识 */
    private Long id;

    /** 栏目名称 */
    private String name;
    /** 父节点ID */
    private Long parentId;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    /** 创建人名称 */
    private String createName;

    /** 创建人id */
    private Long createId;

    /** 逻辑删除:1删除 0:未删除 */
    private Integer colDelete;
    private List<BizColumnTree> children = new ArrayList();

    public BizColumnTree() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Integer getColDelete() {
        return colDelete;
    }

    public void setColDelete(Integer colDelete) {
        this.colDelete = colDelete;
    }

    public List<BizColumnTree> getChildren() {
        return children;
    }

    public void setChildren(List<BizColumnTree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("name", getName())
            .append("createDate", getCreateDate())
            .append("createName", getCreateName())
            .append("createId", getCreateId())
            .append("colDelete", getColDelete())
            .toString();
    }
}
