package com.rongxin.example.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.annotation.Excel;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 示例demo 对象 rx_demo
 * 
 * @author rx
 * @date 2022-07-28
 */
public class RxDemo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /**姓名*/
    @Excel(name = "姓名")
    private String demoName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
    private Long demoSex;

    /** 出生日期 */
    @Excel(name = "出生日期")
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("demoName", getDemoName())
            .append("demoSex", getDemoSex())
            .append("demoBirth", getDemoBirth())
            .toString();
    }
}
