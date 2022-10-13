package com.rongxin.cms.controller;

import java.util.List;
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
import com.rongxin.cms.domain.BizCopyright;
import com.rongxin.cms.service.IBizCopyrightService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * cms版权信息Controller
 * 
 * @author rx
 * @date 2022-10-12
 */
@RestController
@RequestMapping("/cms/copyright")
public class BizCopyrightController extends BaseController
{
    @Autowired
    private IBizCopyrightService bizCopyrightService;

    /**
     * 查询cms版权信息列表
     */
    @ApiOperation("查询cms版权信息列表")
    @PreAuthorize("@ss.hasPermi('cms:copyright:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizCopyright bizCopyright)
    {
        startPage();
        List<BizCopyright> list = bizCopyrightService.selectBizCopyrightList(bizCopyright);
        return getDataTable(list);
    }

    /**
     * 导出cms版权信息列表
     */
    @ApiOperation("导出cms版权信息列表")
    @PreAuthorize("@ss.hasPermi('cms:copyright:export')")
    @Log(title = "cms版权信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizCopyright bizCopyright)
    {
        List<BizCopyright> list = bizCopyrightService.selectBizCopyrightList(bizCopyright);
        ExcelUtil<BizCopyright> util = new ExcelUtil<BizCopyright>(BizCopyright.class);
        util.exportExcel(response, list, "cms版权信息数据");
    }

    /**
     * 获取cms版权信息详细信息
     */
    @ApiOperation("获取cms版权信息详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:copyright:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizCopyrightService.selectBizCopyrightById(id));
    }

    /**
     * 新增cms版权信息
     */
    @ApiOperation("新增cms版权信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:copyright:add')")
    @Log(title = "cms版权信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCopyright bizCopyright)
    {
        return toAjax(bizCopyrightService.insertBizCopyright(bizCopyright));
    }

    /**
     * 修改cms版权信息
     */
    @ApiOperation("修改cms版权信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:copyright:edit')")
    @Log(title = "cms版权信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCopyright bizCopyright)
    {
        return toAjax(bizCopyrightService.updateBizCopyright(bizCopyright));
    }

    /**
     * 删除cms版权信息
     */
    @ApiOperation("删除cms版权信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:copyright:remove')")
    @Log(title = "cms版权信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizCopyrightService.deleteBizCopyrightByIds(ids));
    }
}
