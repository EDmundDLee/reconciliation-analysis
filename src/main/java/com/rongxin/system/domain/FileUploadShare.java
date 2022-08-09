package com.rongxin.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.annotation.Excel;
import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传明细对象 file_upload_share
 * 
 * @author rx
 * @date 2022-08-08
 */
public class FileUploadShare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String filename;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private String filesize;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String fileurl;

    /** 文件组id */
    @Excel(name = "文件组id")
    private String groupid;

    /** 上传人 */
    @Excel(name = "上传人")
    private String createuser;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdate;

    /** 部门编码 */
    @Excel(name = "部门编码")
    private String deptcode;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedate;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateuser;

    /** 是否删除(1 删除,0 未删除) */
    @Excel(name = "是否删除(1 删除,0 未删除)")
    private String isdel;

    /** 备用1 */
    @Excel(name = "备用1")
    private String standby1;

    /** 备用2 */
    @Excel(name = "备用2")
    private String standby2;

    /** 备用3 */
    @Excel(name = "备用3")
    private String standby3;

    /** 备用4 */
    @Excel(name = "备用4")
    private String standby4;

    /** 备用5 */
    @Excel(name = "备用5")
    private String standby5;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setFilesize(String filesize) 
    {
        this.filesize = filesize;
    }

    public String getFilesize() 
    {
        return filesize;
    }
    public void setFileurl(String fileurl) 
    {
        this.fileurl = fileurl;
    }

    public String getFileurl() 
    {
        return fileurl;
    }
    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }
    public void setCreateuser(String createuser) 
    {
        this.createuser = createuser;
    }

    public String getCreateuser() 
    {
        return createuser;
    }
    public void setCreatedate(Date createdate) 
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }
    public void setDeptcode(String deptcode) 
    {
        this.deptcode = deptcode;
    }

    public String getDeptcode() 
    {
        return deptcode;
    }
    public void setUpdatedate(Date updatedate) 
    {
        this.updatedate = updatedate;
    }

    public Date getUpdatedate() 
    {
        return updatedate;
    }
    public void setUpdateuser(String updateuser) 
    {
        this.updateuser = updateuser;
    }

    public String getUpdateuser() 
    {
        return updateuser;
    }
    public void setIsdel(String isdel) 
    {
        this.isdel = isdel;
    }

    public String getIsdel() 
    {
        return isdel;
    }
    public void setStandby1(String standby1) 
    {
        this.standby1 = standby1;
    }

    public String getStandby1() 
    {
        return standby1;
    }
    public void setStandby2(String standby2) 
    {
        this.standby2 = standby2;
    }

    public String getStandby2() 
    {
        return standby2;
    }
    public void setStandby3(String standby3) 
    {
        this.standby3 = standby3;
    }

    public String getStandby3() 
    {
        return standby3;
    }
    public void setStandby4(String standby4) 
    {
        this.standby4 = standby4;
    }

    public String getStandby4() 
    {
        return standby4;
    }
    public void setStandby5(String standby5) 
    {
        this.standby5 = standby5;
    }

    public String getStandby5() 
    {
        return standby5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filename", getFilename())
            .append("filesize", getFilesize())
            .append("fileurl", getFileurl())
            .append("groupid", getGroupid())
            .append("createuser", getCreateuser())
            .append("createdate", getCreatedate())
            .append("deptcode", getDeptcode())
            .append("updatedate", getUpdatedate())
            .append("updateuser", getUpdateuser())
            .append("isdel", getIsdel())
            .append("standby1", getStandby1())
            .append("standby2", getStandby2())
            .append("standby3", getStandby3())
            .append("standby4", getStandby4())
            .append("standby5", getStandby5())
            .append("fileType", getFileType())
            .toString();
    }
}
