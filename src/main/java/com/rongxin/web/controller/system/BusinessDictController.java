package com.rongxin.web.controller.system;

import java.util.List;

import com.rongxin.web.domain.BusinessDict;
import com.rongxin.web.service.IBusinessDictService;
import io.swagger.annotations.Api;
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
import com.rongxin.common.utils.poi.ExcelUtil;

/**
 * 业务字典Controller
 * 
 * @author rx
 * @date 2023-03-14
 */
@Api(tags = "[业务字典]")
@RestController
@RequestMapping("/business/dict")
public class BusinessDictController extends BaseController
{
    @Autowired
    private IBusinessDictService businessDictService;

    /**
     * 查询业务字典列表
     */
    @ApiOperation("查询业务字典列表")
    @PreAuthorize("@ss.hasPermi('business:dict:list')")
    @GetMapping("/list")
    public AjaxResult list(BusinessDict businessDict)
    {
        List<BusinessDict> list = businessDictService.selectBusinessDictList(businessDict);
        return AjaxResult.success(list);
    }

    /**
     * 导出业务字典列表
     */
    @ApiOperation("导出业务字典列表")
    @PreAuthorize("@ss.hasPermi('business:dict:export')")
    @Log(title = "业务字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessDict businessDict)
    {
        List<BusinessDict> list = businessDictService.selectBusinessDictList(businessDict);
        ExcelUtil<BusinessDict> util = new ExcelUtil<BusinessDict>(BusinessDict.class);
        util.exportExcel(response, list, "业务字典数据");
    }

    /**
     * 获取业务字典详细信息
     */
    @ApiOperation("获取业务字典详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('business:dict:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(businessDictService.selectBusinessDictById(id));
    }

    /**
     * 新增业务字典
     */
    @ApiOperation("新增业务字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('business:dict:add')")
    @Log(title = "业务字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessDict businessDict)
    {
        return toAjax(businessDictService.insertBusinessDict(businessDict));
    }

    /**
     * 修改业务字典
     */
    @ApiOperation("修改业务字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('business:dict:edit')")
    @Log(title = "业务字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessDict businessDict)
    {
        return toAjax(businessDictService.updateBusinessDict(businessDict));
    }

    /**
     * 删除业务字典
     */
    @ApiOperation("删除业务字典")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('business:dict:remove')")
    @Log(title = "业务字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long ids)
    {
        return businessDictService.deleteBusinessDictById(ids);
    }
}
