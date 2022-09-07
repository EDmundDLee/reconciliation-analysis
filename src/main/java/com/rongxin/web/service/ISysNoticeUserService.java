package com.rongxin.web.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.web.domain.SysNoticeUser;

/**
 * 用户通知公告关联Service接口
 * 
 * @author rx
 * @date 2022-08-29
 */
public interface ISysNoticeUserService extends IService<SysNoticeUser>
{
    /**
     * 查询用户通知公告关联
     * 
     * @param id 用户通知公告关联主键
     * @return 用户通知公告关联
     */
    public SysNoticeUser selectSysNoticeUserById(String id);

    /**
     * 查询用户通知公告关联列表
     * 
     * @param sysNoticeUser 用户通知公告关联
     * @return 用户通知公告关联集合
     */
    public List<SysNoticeUser> selectSysNoticeUserList(SysNoticeUser sysNoticeUser);

    /**
     * 新增用户通知公告关联
     * 
     * @param sysNoticeUser 用户通知公告关联
     * @return 结果
     */
    public int insertSysNoticeUser(SysNoticeUser sysNoticeUser);

    /**
     * 修改用户通知公告关联
     * 
     * @param sysNoticeUser 用户通知公告关联
     * @return 结果
     */
    public int updateSysNoticeUser(SysNoticeUser sysNoticeUser);

    /**
     * 批量删除用户通知公告关联
     * 
     * @param ids 需要删除的用户通知公告关联主键集合
     * @return 结果
     */
    public int deleteSysNoticeUserByIds(String[] ids);

    /**
     * 删除用户通知公告关联信息
     * 
     * @param id 用户通知公告关联主键
     * @return 结果
     */
    public int deleteSysNoticeUserById(String id);
}
