package com.rongxin.web.service.impl;

import java.util.List;
import com.rongxin.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.web.domain.SysNoticeUser;
import com.rongxin.web.mapper.SysNoticeUserMapper;
import com.rongxin.web.service.ISysNoticeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户通知公告关联Service业务层处理
 * 
 * @author rx
 * @date 2022-08-29
 */
@Service
public class SysNoticeUserServiceImpl extends ServiceImpl<SysNoticeUserMapper, SysNoticeUser> implements ISysNoticeUserService
{
    @Autowired
    private SysNoticeUserMapper sysNoticeUserMapper;

    /**
     * 查询用户通知公告关联
     * 
     * @param id 用户通知公告关联主键
     * @return 用户通知公告关联
     */
    @Override
    public SysNoticeUser selectSysNoticeUserById(String id)
    {
        return sysNoticeUserMapper.selectSysNoticeUserById(id);
    }

    /**
     * 查询用户通知公告关联列表
     * 
     * @param sysNoticeUser 用户通知公告关联
     * @return 用户通知公告关联
     */
    @Override
    public List<SysNoticeUser> selectSysNoticeUserList(SysNoticeUser sysNoticeUser)
    {
        return sysNoticeUserMapper.selectSysNoticeUserList(sysNoticeUser);
    }

    /**
     * 新增用户通知公告关联
     * 
     * @param sysNoticeUser 用户通知公告关联
     * @return 结果
     */
    @Override
    public int insertSysNoticeUser(SysNoticeUser sysNoticeUser)
    {
        sysNoticeUser.setCreateTime(DateUtils.getNowDate());
        return sysNoticeUserMapper.insertSysNoticeUser(sysNoticeUser);
    }

    /**
     * 修改用户通知公告关联
     * 
     * @param sysNoticeUser 用户通知公告关联
     * @return 结果
     */
    @Override
    public int updateSysNoticeUser(SysNoticeUser sysNoticeUser)
    {
        sysNoticeUser.setUpdateTime(DateUtils.getNowDate());
        return sysNoticeUserMapper.updateSysNoticeUser(sysNoticeUser);
    }

    /**
     * 批量删除用户通知公告关联
     * 
     * @param ids 需要删除的用户通知公告关联主键
     * @return 结果
     */
    @Override
    public int deleteSysNoticeUserByIds(String[] ids)
    {
        return sysNoticeUserMapper.deleteSysNoticeUserByIds(ids);
    }

    /**
     * 删除用户通知公告关联信息
     * 
     * @param id 用户通知公告关联主键
     * @return 结果
     */
    @Override
    public int deleteSysNoticeUserById(String id)
    {
        return sysNoticeUserMapper.deleteSysNoticeUserById(id);
    }
}
