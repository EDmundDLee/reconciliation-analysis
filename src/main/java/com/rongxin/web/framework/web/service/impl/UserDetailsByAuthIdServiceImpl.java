package com.rongxin.web.framework.web.service.impl;

import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.core.domain.model.LoginUser;
import com.rongxin.common.enums.UserStatus;
import com.rongxin.common.exception.ServiceException;
import com.rongxin.common.utils.StringUtils;
import com.rongxin.web.framework.web.service.SysPermissionService;
import com.rongxin.web.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author rx
 */
@Service("userDetailsByAuthIdService")
public class UserDetailsByAuthIdServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsByAuthIdServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByAuthId(authId);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", authId);
            throw new ServiceException("登录用户：信息异常");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
