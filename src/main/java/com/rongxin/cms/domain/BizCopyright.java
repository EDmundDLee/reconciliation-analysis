package com.rongxin.cms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * cms版权信息对象 biz_copyright
 * 
 * @author rx
 * @date 2022-10-12
 */
public class BizCopyright extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 版权 */
    @Excel(name = "版权")
    private String copyright;

    /** 联系微信号 */
    @Excel(name = "联系微信号")
    private String wx;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 创建人名称 */
    @Excel(name = "创建人名称")
    private String createName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 逻辑删除:0未删除 1:已删除 */
    @Excel(name = "逻辑删除:0未删除 1:已删除")
    private Long isdel;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createId;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipCode;

    /** 版权所有 */
    @Excel(name = "版权所有")
    private String copyrightName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCopyright(String copyright) 
    {
        this.copyright = copyright;
    }

    public String getCopyright() 
    {
        return copyright;
    }
    public void setWx(String wx) 
    {
        this.wx = wx;
    }

    public String getWx() 
    {
        return wx;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
    public void setIsdel(Long isdel) 
    {
        this.isdel = isdel;
    }

    public Long getIsdel() 
    {
        return isdel;
    }
    public void setCreateId(Long createId) 
    {
        this.createId = createId;
    }

    public Long getCreateId() 
    {
        return createId;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setCopyrightName(String copyrightName) 
    {
        this.copyrightName = copyrightName;
    }

    public String getCopyrightName() 
    {
        return copyrightName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("copyright", getCopyright())
            .append("wx", getWx())
            .append("address", getAddress())
            .append("createName", getCreateName())
            .append("createDate", getCreateDate())
            .append("isdel", getIsdel())
            .append("createId", getCreateId())
            .append("zipCode", getZipCode())
            .append("copyrightName", getCopyrightName())
            .toString();
    }
}
