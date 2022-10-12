package com.rongxin.cms.controller;

import com.rongxin.cms.domain.BizLink;
import com.rongxin.cms.service.IBizLinkService;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.page.TableDataInfo;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.common.utils.poi.ExcelUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller
 * 
 * @author rx
 * @date 2022-10-09
 */
@RestController
@RequestMapping("/cms/link")
public class BizLinkController extends BaseController
{
    @Autowired
    private IBizLinkService bizLinkService;


    /**
     * 查询列表
     */
    @ApiOperation("查询列表")
    @PreAuthorize("@ss.hasPermi('cms:link:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizLink bizLink)
    {
        startPage();
        List<BizLink> list = bizLinkService.selectBizLinkList(bizLink);
        return getDataTable(list);
    }

    /**
     * 导出列表
     */
    @ApiOperation("导出列表")
    @PreAuthorize("@ss.hasPermi('cms:link:export')")
    @Log(title = "", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizLink bizLink)
    {
        List<BizLink> list = bizLinkService.selectBizLinkList(bizLink);
        ExcelUtil<BizLink> util = new ExcelUtil<BizLink>(BizLink.class);
        util.exportExcel(response, list, "数据");
    }

    /**
     * 获取详细信息
     */
    @ApiOperation("获取详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:link:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizLinkService.selectBizLinkById(id));
    }

    /**
     * 新增
     */
    @ApiOperation("新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:link:add')")
    @Log(title = "", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(MultipartFile file, BizLink bizLink) throws IOException
    {
        return toAjax(bizLinkService.insertBizLink(file, bizLink));
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:link:edit')")
    @Log(title = "", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(MultipartFile file, BizLink bizLink) throws IOException
    {
        return toAjax(bizLinkService.updateBizLink(file, bizLink));
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:link:remove')")
    @Log(title = "", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizLinkService.deleteBizLinkByIds(ids));
    }
}
