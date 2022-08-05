package com.rongxin.demo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 示例功能对象 rx_demo
 * 
 * @author rx
 * @date 2022-08-05
 */
public class RxDemo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String demoName;

    /** 性别 */
    @Excel(name = "性别")
    private Long demoSex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date demoBirth;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDemoName(String demoName) 
    {
        this.demoName = demoName;
    }

    public String getDemoName() 
    {
        return demoName;
    }
    public void setDemoSex(Long demoSex) 
    {
        this.demoSex = demoSex;
    }

    public Long getDemoSex() 
    {
        return demoSex;
    }
    public void setDemoBirth(Date demoBirth) 
    {
        this.demoBirth = demoBirth;
    }

    public Date getDemoBirth() 
    {
        return demoBirth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("demoName", getDemoName())
            .append("demoSex", getDemoSex())
            .append("demoBirth", getDemoBirth())
            .toString();
    }
}
