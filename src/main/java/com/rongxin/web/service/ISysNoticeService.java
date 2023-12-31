package com.rongxin.web.service;

import java.io.IOException;
import java.util.List;
import com.rongxin.web.domain.SysNotice;

/**
 * 公告 服务层
 * 
 * @author rx
 */
public interface ISysNoticeService
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice) throws IOException;

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 删除公告信息
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);
    
    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);

    /**
     * 通过userid查询未读消息列表
     *
     * @param userId sys_notice_user(关联表user_id)
     * @return 公告集合
     */
    public List<SysNotice> selectMessageList(String userId);


    /**
     * 查询公告信息及修改信息状态
     *
     * @param notice 消息信息
     * @return 消息信息
     */
    public SysNotice selectMessageById(SysNotice notice);

}
