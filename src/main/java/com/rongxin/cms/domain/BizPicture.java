package com.rongxin.cms.domain;

import com.rongxin.common.annotation.Excel;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 文章内容图片信息
 * 
 * @author rx
 * @date 2022-10-09
 */
public class BizPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 内容 */
    private Long articleId;

    /** 路径 */
    @Excel(name = "路径")
    private String url;

    /** 描述 */
    @Excel(name = "描述")
    private String picDescribe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicDescribe() {
        return picDescribe;
    }

    public void setPicDescribe(String picDescribe) {
        this.picDescribe = picDescribe;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("articleId", getArticleId())
            .append("url", getUrl())
            .append("picDescribe", getPicDescribe())
            .toString();
    }
}
