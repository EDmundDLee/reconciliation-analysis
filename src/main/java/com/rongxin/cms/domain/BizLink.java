package com.rongxin.cms.domain;

import com.rongxin.common.annotation.Excel;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 对象 biz_link
 * 
 * @author rx
 * @date 2022-10-09
 */
public class BizLink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识id */
    private Long id;

    /** 链接标题 */
    @Excel(name = "链接标题")
    private String title;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String imgUrl;

    /** 导航链接 */
    @Excel(name = "导航链接")
    private String linkUrl;

    /** 创建人名称 */
    @Excel(name = "创建人名称", readConverterExp = "$column.readConverterExp()")
    private String createName;

    /** 创建时间 */
    @Excel(name = "创建时间", readConverterExp = "$column.readConverterExp()")
    private Date createDate;

    /** 逻辑删除:0未删除 1:已删除 */
    @Excel(name = "逻辑删除:0未删除 1:已删除")
    private Long isDel;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createId;

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setLinkUrl(String linkUrl) 
    {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() 
    {
        return linkUrl;
    }
    public void setCreateName(String createName) 
    {
        this.createName = createName;
    }

    public String getCreateName() 
    {
        return createName;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    public Long getIsDel() {
        return isDel;
    }

    public void setIsDel(Long isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("imgUrl", getImgUrl())
            .append("linkUrl", getLinkUrl())
            .append("createName", getCreateName())
            .append("createDate", getCreateDate())
            .append("isDel", getIsDel())
            .append("create_id", getCreateId())
            .toString();
    }
}
