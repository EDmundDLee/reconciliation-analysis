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
import com.rongxin.cms.domain.BizRule;
import com.rongxin.cms.service.IBizRuleService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 内容拓展属性规则表Controller
 * 
 * @author rx
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/cms/rule")
public class BizRuleController extends BaseController
{
    @Autowired
    private IBizRuleService bizRuleService;

    /**
     * 查询内容拓展属性规则表列表
     */
    @ApiOperation("查询内容拓展属性规则表列表")
    @PreAuthorize("@ss.hasPermi('cms:rule:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizRule bizRule)
    {
        startPage();
        List<BizRule> list = bizRuleService.selectBizRuleList(bizRule);
        return getDataTable(list);
    }

    /**
     * 导出内容拓展属性规则表列表
     */
    @ApiOperation("导出内容拓展属性规则表列表")
    @PreAuthorize("@ss.hasPermi('cms:rule:export')")
    @Log(title = "内容拓展属性规则表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizRule bizRule)
    {
        List<BizRule> list = bizRuleService.selectBizRuleList(bizRule);
        ExcelUtil<BizRule> util = new ExcelUtil<BizRule>(BizRule.class);
        util.exportExcel(response, list, "内容拓展属性规则表数据");
    }

    /**
     * 获取内容拓展属性规则表详细信息
     */
    @ApiOperation("获取内容拓展属性规则表详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:rule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizRuleService.selectBizRuleById(id));
    }

    /**
     * 新增内容拓展属性规则表
     */
    @ApiOperation("新增内容拓展属性规则表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:rule:add')")
    @Log(title = "内容拓展属性规则表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizRule bizRule)
    {
        return toAjax(bizRuleService.insertBizRule(bizRule));
    }

    /**
     * 修改内容拓展属性规则表
     */
    @ApiOperation("修改内容拓展属性规则表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('cms:rule:edit')")
    @Log(title = "内容拓展属性规则表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizRule bizRule)
    {
        return toAjax(bizRuleService.updateBizRule(bizRule));
    }

    /**
     * 删除内容拓展属性规则表
     */
    @ApiOperation("删除内容拓展属性规则表")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:rule:remove')")
    @Log(title = "内容拓展属性规则表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizRuleService.deleteBizRuleByIds(ids));
    }
}
