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
import com.rongxin.cms.domain.BizAttributeValue;
import com.rongxin.cms.service.IBizAttributeValueService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 内容拓展属性值Controller
 * 
 * @author rx
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/cms/attvalue")
public class BizAttributeValueController extends BaseController
{
    @Autowired
    private IBizAttributeValueService bizAttributeValueService;

    /**
     * 查询内容拓展属性值列表
     */
    @ApiOperation("查询内容拓展属性值列表")
    @PreAuthorize("@ss.hasPermi('cms:attvalue:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAttributeValue bizAttributeValue)
    {
        startPage();
        List<BizAttributeValue> list = bizAttributeValueService.selectBizAttributeValueList(bizAttributeValue);
        return getDataTable(list);
    }

    /**
     * 导出内容拓展属性值列表
     */
    @ApiOperation("导出内容拓展属性值列表")
    @PreAuthorize("@ss.hasPermi('cms:attvalue:export')")
    @Log(title = "内容拓展属性值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizAttributeValue bizAttributeValue)
    {
        List<BizAttributeValue> list = bizAttributeValueService.selectBizAttributeValueList(bizAttributeValue);
        ExcelUtil<BizAttributeValue> util = new ExcelUtil<BizAttributeValue>(BizAttributeValue.class);
        util.exportExcel(response, list, "内容拓展属性值数据");
    }

    /**
     * 获取内容拓展属性值详细信息
     */
    @ApiOperation("获取内容拓展属性值详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:attvalue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizAttributeValueService.selectBizAttributeValueById(id));
    }

    /**
     * 新增内容拓展属性值
     */
    @ApiOperation("新增内容拓展属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:attvalue:add')")
    @Log(title = "内容拓展属性值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAttributeValue bizAttributeValue)
    {
        return toAjax(bizAttributeValueService.insertBizAttributeValue(bizAttributeValue));
    }

    /**
     * 修改内容拓展属性值
     */
    @ApiOperation("修改内容拓展属性值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:attvalue:edit')")
    @Log(title = "内容拓展属性值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAttributeValue bizAttributeValue)
    {
        return toAjax(bizAttributeValueService.updateBizAttributeValue(bizAttributeValue));
    }

    /**
     * 删除内容拓展属性值
     */
    @ApiOperation("删除内容拓展属性值")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:attvalue:remove')")
    @Log(title = "内容拓展属性值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizAttributeValueService.deleteBizAttributeValueByIds(ids));
    }
}
