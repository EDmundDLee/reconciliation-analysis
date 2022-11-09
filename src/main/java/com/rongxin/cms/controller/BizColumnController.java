package com.rongxin.cms.controller;

import java.util.List;
import java.util.Map;

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
import com.rongxin.cms.domain.BizColumn;
import com.rongxin.cms.service.IBizColumnService;
import com.rongxin.common.utils.poi.ExcelUtil;

/**
 * 栏目类别Controller
 * 
 * @author rx
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/cms/cmscol")
public class BizColumnController extends BaseController
{
    @Autowired
    private IBizColumnService bizColumnService;

    /**
     * 查询栏目类别列表
     */
    @ApiOperation("查询栏目类别列表")
    @PreAuthorize("@ss.hasPermi('cms:cmscol:list')")
    @GetMapping("/list")
    public AjaxResult list(BizColumn bizColumn)
    {
        List<BizColumn> list = bizColumnService.selectBizColumnList(bizColumn);
        return AjaxResult.success(list);
    }

    /**
     * 导出栏目类别列表
     */
    @ApiOperation("导出栏目类别列表")
    @PreAuthorize("@ss.hasPermi('cms:cmscol:export')")
    @Log(title = "栏目类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizColumn bizColumn)
    {
        List<BizColumn> list = bizColumnService.selectBizColumnList(bizColumn);
        ExcelUtil<BizColumn> util = new ExcelUtil<BizColumn>(BizColumn.class);
        util.exportExcel(response, list, "栏目类别数据");
    }

    /**
     * 获取栏目类别详细信息
     */
    @ApiOperation("获取栏目类别详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:cmscol:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizColumnService.selectBizColumnById(id));
    }

    /**
     * 新增栏目类别
     */
    @ApiOperation("新增栏目类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:cmscol:add')")
    @Log(title = "栏目类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizColumn bizColumn)
    {
        return toAjax(bizColumnService.insertBizColumn(bizColumn));
    }

    /**
     * 修改栏目类别
     */
    @ApiOperation("修改栏目类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:cmscol:edit')")
    @Log(title = "栏目类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizColumn bizColumn)
    {
        return toAjax(bizColumnService.updateBizColumn(bizColumn));
    }

    /**
     * 删除栏目类别
     */
    @ApiOperation("删除栏目类别")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:cmscol:remove')")
    @Log(title = "栏目类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizColumnService.deleteBizColumnByIds(ids));
    }

    @ApiOperation("获取规则属性")
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "获取规则属性", businessType = BusinessType.DELETE)
    @PostMapping("/getRuleAttr")
    public AjaxResult getRuleAttr()
    {
        return AjaxResult.success(bizColumnService.getRuleAttr());
    }

    @ApiOperation("绑定规则")
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "绑定规则", businessType = BusinessType.DELETE)
    @PostMapping("/bindRule")
    public AjaxResult bindRule(@RequestBody Map<String,Object> map)
    {
        return AjaxResult.success(bizColumnService.bindRule(map));
    }
}
