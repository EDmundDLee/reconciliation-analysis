package com.rongxin.web.service.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.rongxin.common.constant.Constants;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.core.domain.model.LoginUser;
import com.rongxin.common.core.redis.RedisCache;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.uuid.IdUtils;
import com.rongxin.framework.websocket.WebSocketServer;
import com.rongxin.web.domain.SysNotice;
import com.rongxin.web.domain.SysNoticeUser;
import com.rongxin.web.framework.aspectj.LogAspect;
import com.rongxin.web.mapper.SysNoticeMapper;
import com.rongxin.web.mapper.SysNoticeUserMapper;
import com.rongxin.web.mapper.SysUserMapper;
import com.rongxin.web.service.ISysNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告 服务层实现
 * 
 * @author rx
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    private static final Logger log = LoggerFactory.getLogger(SysNoticeServiceImpl.class);
    @Autowired
    private SysNoticeMapper noticeMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysNoticeUserMapper sysNoticeUserMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private WebSocketServer webSocketServer;


    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertNotice(SysNotice notice) throws IOException
    {
        //获取当前登录用户
        SysUser user = SecurityUtils.getLoginUser().getUser();
        int n = noticeMapper.insertNotice(notice);
        List<SysUser> userList = sysUserMapper.selectUserList(new SysUser());
        for (int i = 0; i < userList.size(); i++) {
            SysUser su = userList.get(i);
            SysNoticeUser snu = new SysNoticeUser();
            snu.setId(IdUtils.simpleUUID());
            snu.setNoticeId(notice.getNoticeId().toString());
            snu.setUserId(su.getUserId().toString());
            snu.setCreateBy(user.getUserId().toString());
            snu.setCreateTime(new Date());
            n = sysNoticeUserMapper.insertSysNoticeUser(snu);
        }
        Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
        for (String key : keys) {
            log.info( key);
            log.info( key);        log.info( key);        log.info( key);        log.info( key);        log.info( key);





            log.info( redisCache.getCacheObject(key));
            LoginUser lUser = redisCache.getCacheObject(key);
            //前端发送消息
            webSocketServer.sendInfo("有新消息!", lUser.getUsername());
        }
        return n;
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    /**
     * 通过userid查询未读消息列表
     *
     * @param userId sys_notice_user(关联表user_id)
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectMessageList(String userId) {
        return noticeMapper.selectMessageList(userId);
    }


    /**
     * 查询公告信息及修改信息状态
     *
     * @param notice 消息信息
     * @return 消息信息
     */
    @Override
    public SysNotice selectMessageById(SysNotice notice)
    {
        SysNoticeUser su = new SysNoticeUser();
        su.setId(notice.getSnuId());
        su.setStatus("1");
        sysNoticeUserMapper.updateSysNoticeUser(su);
        return noticeMapper.selectNoticeById(notice.getNoticeId());
    }
}
