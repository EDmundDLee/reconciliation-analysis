package com.rongxin.web.controller.system;

import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.web.domain.SysNoticeUser;
import com.rongxin.web.service.ISysNoticeUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 用户通知公告关联Controller
 * 
 * @author rx
 * @date 2022-08-29
 */
@RestController
@RequestMapping("/system/noticeuser")
public class SysNoticeUserController extends BaseController
{
    @Autowired
    private ISysNoticeUserService sysNoticeUserService;

    /**
     * 查询用户通知公告关联列表
     */
    @ApiOperation("查询用户通知公告关联列表")
    @PreAuthorize("@ss.hasPermi('system:noticeuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNoticeUser sysNoticeUser)
    {
        startPage();
        List<SysNoticeUser> list = sysNoticeUserService.selectSysNoticeUserList(sysNoticeUser);
        return getDataTable(list);
    }

    /**
     * 导出用户通知公告关联列表
     */
    @ApiOperation("导出用户通知公告关联列表")
    @PreAuthorize("@ss.hasPermi('system:noticeuser:export')")
    @Log(title = "用户通知公告关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysNoticeUser sysNoticeUser)
    {
        List<SysNoticeUser> list = sysNoticeUserService.selectSysNoticeUserList(sysNoticeUser);
        ExcelUtil<SysNoticeUser> util = new ExcelUtil<SysNoticeUser>(SysNoticeUser.class);
        util.exportExcel(response, list, "用户通知公告关联数据");
    }

    /**
     * 获取用户通知公告关联详细信息
     */
    @ApiOperation("获取用户通知公告关联详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('system:noticeuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysNoticeUserService.selectSysNoticeUserById(id));
    }

    /**
     * 新增用户通知公告关联
     */
    @ApiOperation("新增用户通知公告关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('system:noticeuser:add')")
    @Log(title = "用户通知公告关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysNoticeUser sysNoticeUser)
    {
        return toAjax(sysNoticeUserService.insertSysNoticeUser(sysNoticeUser));
    }

    /**
     * 修改用户通知公告关联
     */
    @ApiOperation("修改用户通知公告关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('system:noticeuser:edit')")
    @Log(title = "用户通知公告关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysNoticeUser sysNoticeUser)
    {
        return toAjax(sysNoticeUserService.updateSysNoticeUser(sysNoticeUser));
    }

    /**
     * 删除用户通知公告关联
     */
    @ApiOperation("删除用户通知公告关联")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('system:noticeuser:remove')")
    @Log(title = "用户通知公告关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysNoticeUserService.deleteSysNoticeUserByIds(ids));
    }
}
