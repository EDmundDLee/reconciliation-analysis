package com.rongxin.cms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 文章内容对象 biz_article
 * 
 * @author rx
 * @date 2022-10-09
 */
public class BizArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 栏目id */
    private Long columnId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 时间 */
    private Date createDate;

    /** 创建人 */
    private String createName;

    /** 创建id */
    private Long createId;

    /** 标题图片id */
    private Long titleImgId;

    /** 1:草稿 2:发布 */
    @Excel(name = "1:草稿 2:发布")
    private Integer artType;

    /** 逻辑删除1:删除0:为删除 */
    private Integer artDelete;

    /** 文章标题图片 */
    private String titleImgUrl;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
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
    public void setTitleImgId(Long titleImgId) 
    {
        this.titleImgId = titleImgId;
    }

    public Long getTitleImgId() 
    {
        return titleImgId;
    }
    public void setArtType(Integer artType) 
    {
        this.artType = artType;
    }

    public Integer getArtType() 
    {
        return artType;
    }
    public void setArtDelete(Integer artDelete) 
    {
        this.artDelete = artDelete;
    }

    public Integer getArtDelete() 
    {
        return artDelete;
    }
    public void setTitleImgUrl(String titleImgUrl) 
    {
        this.titleImgUrl = titleImgUrl;
    }

    public String getTitleImgUrl() 
    {
        return titleImgUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("columnId", getColumnId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("createDate", getCreateDate())
            .append("createName", getCreateName())
            .append("createId", getCreateId())
            .append("titleImgId", getTitleImgId())
            .append("artType", getArtType())
            .append("artDelete", getArtDelete())
            .append("titleImgUrl", getTitleImgUrl())
            .toString();
    }
}
