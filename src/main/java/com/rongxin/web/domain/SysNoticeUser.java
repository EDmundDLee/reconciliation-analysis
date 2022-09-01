package com.rongxin.web.domain;

import com.rongxin.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rongxin.common.annotation.Excel;

/**
 * 用户通知公告关联对象 sys_notice_user
 *
 * @author rx
 * @date 2022-08-29
 */
public class SysNoticeUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识ID */
    @Excel(name = "标识ID")
    private String noticeId;

    /** 已读状态（0未读 1已读） */
    @Excel(name = "已读状态", readConverterExp = "0=未读,1=已读")
    private String status;

    /** 主键 */
    private String id;

    /** 用户主键 */
    @Excel(name = "用户主键")
    private String userId;

    public void setNoticeId(String noticeId)
    {
        this.noticeId = noticeId;
    }

    public String getNoticeId()
    {
        return noticeId;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("noticeId", getNoticeId())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("id", getId())
                .append("userId", getUserId())
                .toString();
    }
}
