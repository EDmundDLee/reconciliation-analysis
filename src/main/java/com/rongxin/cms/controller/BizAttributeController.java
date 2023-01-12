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
import com.rongxin.cms.domain.BizAttribute;
import com.rongxin.cms.service.IBizAttributeService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 内容拓展属性Controller
 * 
 * @author rx
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/cms/attribute")
public class BizAttributeController extends BaseController
{
    @Autowired
    private IBizAttributeService bizAttributeService;

    /**
     * 查询内容拓展属性列表
     */
    @ApiOperation("查询内容拓展属性列表")
    @PreAuthorize("@ss.hasPermi('cms:attribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAttribute bizAttribute)
    {
        List<BizAttribute> list = bizAttributeService.selectBizAttributeList(bizAttribute);
        return getDataTable(list);
    }

    /**
     * 导出内容拓展属性列表
     */
    @ApiOperation("导出内容拓展属性列表")
    @PreAuthorize("@ss.hasPermi('cms:attribute:export')")
    @Log(title = "内容拓展属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizAttribute bizAttribute)
    {
        List<BizAttribute> list = bizAttributeService.selectBizAttributeList(bizAttribute);
        ExcelUtil<BizAttribute> util = new ExcelUtil<BizAttribute>(BizAttribute.class);
        util.exportExcel(response, list, "内容拓展属性数据");
    }

    /**
     * 获取内容拓展属性详细信息
     */
    @ApiOperation("获取内容拓展属性详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:attribute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizAttributeService.selectBizAttributeById(id));
    }

    /**
     * 新增内容拓展属性
     */
    @ApiOperation("新增内容拓展属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:attribute:add')")
    @Log(title = "内容拓展属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAttribute bizAttribute)
    {
        return toAjax(bizAttributeService.insertBizAttribute(bizAttribute));
    }

    /**
     * 修改内容拓展属性
     */
    @ApiOperation("修改内容拓展属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:attribute:edit')")
    @Log(title = "内容拓展属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAttribute bizAttribute)
    {
        return toAjax(bizAttributeService.updateBizAttribute(bizAttribute));
    }

    /**
     * 删除内容拓展属性
     */
    @ApiOperation("删除内容拓展属性")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:attribute:remove')")
    @Log(title = "内容拓展属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizAttributeService.deleteBizAttributeByIds(ids));
    }
}
