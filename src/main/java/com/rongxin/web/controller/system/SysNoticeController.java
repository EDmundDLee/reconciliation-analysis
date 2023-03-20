package com.rongxin.web.controller.system;

import java.io.IOException;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.page.TableDataInfo;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.web.domain.SysNotice;
import com.rongxin.web.service.ISysNoticeService;

/**
 * 公告 信息操作处理
 * 
 * @author rx
 */
@Api(tags = "[公告信息]")
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @ApiOperation("获取通知公告列表")
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @ApiOperation("根据通知公告编号获取详细信息")
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @ApiOperation("新增通知公告")
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice) throws IOException
    {
        notice.setCreateBy(getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @ApiOperation("修改通知公告")
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNotice notice)
    {
        notice.setUpdateBy(getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @ApiOperation("删除通知公告")
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }

    /**
     * 通过userid查询未读消息列表
     */
    @ApiOperation("通过userid查询未读消息列表")
    @PreAuthorize("@ss.hasPermi('system:notice:messageList')")
    @GetMapping("/messageList")
    public TableDataInfo messageList(String userId)
    {
        startPage();
        List<SysNotice> messageList = noticeService.selectMessageList(userId);
        return getDataTable(messageList);
    }
    /**
     * 通过userid查询消息列表
     */
    @ApiOperation("通过userid查询消息列表")
    @GetMapping("/messageListOfAll")
    public TableDataInfo messageListOfAll(String userId)
    {
        startPage();
        List<SysNotice> messageList = noticeService.selectMessageList(userId);
        return getDataTable(messageList);
    }
    /**
     * 根据通知公告编号获取详细信息及修改消息状态
     */
    @ApiOperation("根据通知公告编号获取详细信息及修改消息状态")
    @PreAuthorize("@ss.hasPermi('system:notice:queryMessage')")
    @GetMapping(value = "/queryMessage")
    public AjaxResult getMessageInfo(SysNotice notice)
    {
        return AjaxResult.success(noticeService.selectMessageById(notice));
    }
    /**
     * 根据通知公告编号获取详细信息及修改消息状态
     */
    @GetMapping(value = "/queryMessageOfAll")
    public AjaxResult queryMessageOfAll(SysNotice notice)
    {
        return AjaxResult.success(noticeService.selectMessageById(notice));
    }
}
