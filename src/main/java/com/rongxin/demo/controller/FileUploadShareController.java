package com.rongxin.demo.controller;

import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.page.TableDataInfo;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.demo.domain.FileUploadShare;
import com.rongxin.demo.service.IFileUploadShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
     * 文件转换
     */
    @PreAuthorize("@ss.hasPermi('system:share:edit')")
    @Log(title = "文件转换", businessType = BusinessType.UPDATE)
    @PutMapping("/handleTurn")
    public AjaxResult handleTurn(@RequestBody String id) throws FileNotFoundException {
        return toAjax(fileUploadShareService.handleTurn(id));
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
