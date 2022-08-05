package com.rongxin.demo.controller;

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
import com.rongxin.demo.domain.RxDemo;
import com.rongxin.demo.service.IRxDemoService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 示例功能Controller
 * 
 * @author rx
 * @date 2022-08-05
 */
@RestController
@RequestMapping("/example/demo")
public class RxDemoController extends BaseController
{
    @Autowired
    private IRxDemoService rxDemoService;

    /**
     * 查询示例功能列表
     */
    @ApiOperation("查询示例功能列表")
    @PreAuthorize("@ss.hasPermi('example:demo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RxDemo rxDemo)
    {
        startPage();
        List<RxDemo> list = rxDemoService.selectRxDemoList(rxDemo);
        return getDataTable(list);
    }

    /**
     * 导出示例功能列表
     */
    @ApiOperation("导出示例功能列表")
    @PreAuthorize("@ss.hasPermi('example:demo:export')")
    @Log(title = "示例功能", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RxDemo rxDemo)
    {
        List<RxDemo> list = rxDemoService.selectRxDemoList(rxDemo);
        ExcelUtil<RxDemo> util = new ExcelUtil<RxDemo>(RxDemo.class);
        util.exportExcel(response, list, "示例功能数据");
    }

    /**
     * 获取示例功能详细信息
     */
    @ApiOperation("获取示例功能详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:demo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(rxDemoService.selectRxDemoById(id));
    }

    /**
     * 新增示例功能
     */
    @ApiOperation("新增示例功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('example:demo:add')")
    @Log(title = "示例功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RxDemo rxDemo)
    {
        return toAjax(rxDemoService.insertRxDemo(rxDemo));
    }

    /**
     * 修改示例功能
     */
    @ApiOperation("修改示例功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('example:demo:edit')")
    @Log(title = "示例功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RxDemo rxDemo)
    {
        return toAjax(rxDemoService.updateRxDemo(rxDemo));
    }

    /**
     * 删除示例功能
     */
    @ApiOperation("删除示例功能")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:demo:remove')")
    @Log(title = "示例功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rxDemoService.deleteRxDemoByIds(ids));
    }
}
