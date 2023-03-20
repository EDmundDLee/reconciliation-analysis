package com.rongxin.web.controller.system;

import com.rongxin.common.constant.Constants;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.domain.entity.SysMenu;
import com.rongxin.common.core.domain.entity.SysRole;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.core.domain.model.LoginBody;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.web.framework.web.service.SysLoginService;
import com.rongxin.web.framework.web.service.SysPermissionService;
import com.rongxin.web.service.ISysMenuService;
import com.rongxin.web.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author rx
 */
@Api(tags = "[登录验证]")
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;
    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("获取用户角色信息")
    @GetMapping("getRoles")
    public AjaxResult getRoles()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        List<SysRole> roles = sysRoleService.selectRoleListByUserId(user.getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("roles", roles);
        return ajax;
    }
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("通过角色获取用户角色信息")
    @GetMapping("GetInfoByRole")
    public AjaxResult GetInfoByRole()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roleList = user.getRoles();
        Long roleId = null;
        AjaxResult ajax = AjaxResult.success();
        if(roleList.size()>0){
            roleId = roleList.get(0).getRoleId();
            // 权限集合
            Set<String> permissions = permissionService.getMenuPermissionByRoleId(user,roleId);
            ajax.put("roles", sysRoleService.selectRoleById(roleId).getRoleName());
            ajax.put("permissions", permissions);

        }else{
            ajax.put("roles", null);
            ajax.put("permissions", null);
        }
        ajax.put("user", user);
        return ajax;
    }
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("通过角色ID获取用户角色信息")
    @GetMapping("GetInfoByRoleId/{roleId}")
    public AjaxResult GetInfoByRoleId(@PathVariable Long roleId)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roleList = user.getRoles();
        List<SysRole> roleListResult = new ArrayList<>();
        for(SysRole role:roleList){
            if(role.getRoleId() == roleId){
                roleListResult.add(role);
            }
        }
        user.setRoles(roleListResult);
        user.setRoleId(roleId);
        user.setRoleIds(new Long[]{roleId});
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermissionByRoleId(user,roleId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", sysRoleService.selectRoleById(roleId).getRoleName());
        ajax.put("permissions", permissions);
        return ajax;
    }
    /**
     * 根据用户角色ID获取权限信息
     *
     * @return 用户信息
     */
    @ApiOperation("根据用户角色ID获取权限信息")
    @GetMapping("getPermissionsByRoleId/{roleId}")
    public AjaxResult getPermissionsByRoleId(@PathVariable Long roleId)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermissionByRoleId(user,roleId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("permissions", permissions);
        ajax.put("roles", sysRoleService.selectRoleById(roleId).getRoleName());
        return ajax;
    }
    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("generateRoutesByRoleId/{roleId}")
    public AjaxResult generateRoutesByRoleId(@PathVariable Long roleId)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserIdAndRoleId(userId,roleId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("generateRoutesByRole")
    public AjaxResult generateRoutesByRole()
    {
        Long userId = SecurityUtils.getUserId();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roleList = user.getRoles();
        Long roleId = null;
        List<SysMenu> menus = new ArrayList<>();
        if(roleList.size()>0){
            roleId = roleList.get(0).getRoleId();
            menus = menuService.selectMenuTreeByUserIdAndRoleId(userId,roleId);
        }
         return AjaxResult.success(menuService.buildMenus(menus));
    }
}
