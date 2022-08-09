package com.rongxin.system.controller;

import java.io.IOException;
import java.util.List;
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
import com.rongxin.system.domain.FileUploadShare;
import com.rongxin.system.service.IFileUploadShareService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传明细Controller
 *
 * @author rx
 * @date 2022-08-08
 */
@RestController
@RequestMapping("/system/share")
public class FileUploadShareController extends BaseController
{
    @Autowired
    private IFileUploadShareService fileUploadShareService;

    /**
     * 查询文件上传明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:share:list')")
    @GetMapping("/list")
    public TableDataInfo list(FileUploadShare fileUploadShare)
    {
        startPage();
        List<FileUploadShare> list = fileUploadShareService.selectFileUploadShareList(fileUploadShare);
        return getDataTable(list);
    }

    /**
     * 导出文件上传明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:share:export')")
    @Log(title = "文件上传明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FileUploadShare fileUploadShare)
    {
        List<FileUploadShare> list = fileUploadShareService.selectFileUploadShareList(fileUploadShare);
        ExcelUtil<FileUploadShare> util = new ExcelUtil<FileUploadShare>(FileUploadShare.class);
        util.exportExcel(response, list, "文件上传明细数据");
    }

    /**
     * 获取文件上传明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:share:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fileUploadShareService.selectFileUploadShareById(id));
    }

    /**
     * 新增文件上传明细
     */
    @PreAuthorize("@ss.hasPermi('system:share:add')")
    @Log(title = "文件上传明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(MultipartFile[] file) throws IOException
    {
        return toAjax(fileUploadShareService.insertFileUploadShare(file));
    }

    /**
     * 修改文件上传明细
     */
    @PreAuthorize("@ss.hasPermi('system:share:edit')")
    @Log(title = "文件上传明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FileUploadShare fileUploadShare)
    {
        return toAjax(fileUploadShareService.updateFileUploadShare(fileUploadShare));
    }

    /**
     * 删除文件上传明细
     */
    @PreAuthorize("@ss.hasPermi('system:share:remove')")
    @Log(title = "文件上传明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fileUploadShareService.deleteFileUploadShareByIds(ids));
    }
}
