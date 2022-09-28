package com.rongxin.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rongxin.common.annotation.Excel;
import com.rongxin.common.core.domain.BaseEntity;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 示例功能对象 rx_demo
 * 
 * @author rx
 * @date 2022-07-29
 */
public class ActivitiHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //任务id
    private String taskId;
    //实例id
    private String instanceId;
    //任务名称
    private String taskName;
    //处置人
    private String assignee;
    //任务开始时间
    private String startTime;
    //任务结束时间
    private String endTime;
    //评论时间
    private String commentTime;
    //评论内容
    private String comment;

    private List<Comment> commentList;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("instanceId", getInstanceId())
            .append("taskName", getTaskName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("startTime", getStartTime())
            .append("commentTime", getCommentTime())
            .append("comment", getComment())
            .append("commentList", getCommentList())
            .toString();
    }
}
