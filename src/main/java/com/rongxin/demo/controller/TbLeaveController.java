package com.rongxin.demo.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
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
import com.rongxin.demo.domain.TbLeave;
import com.rongxin.demo.service.ITbLeaveService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 请假示例Controller
 * 
 * @author rx
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/example/leave")
public class TbLeaveController extends BaseController
{
    @Autowired
    private ITbLeaveService tbLeaveService;

    /**
     * 查询请假示例列表
     */
    @ApiOperation("查询请假示例列表")
    @PreAuthorize("@ss.hasPermi('example:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbLeave tbLeave)
    {
        startPage();
        List<TbLeave> list = tbLeaveService.selectTbLeaveList(tbLeave);
        return getDataTable(list);
    }

    /**
     * 导出请假示例列表
     */
    @ApiOperation("导出请假示例列表")
    @PreAuthorize("@ss.hasPermi('example:leave:export')")
    @Log(title = "请假示例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbLeave tbLeave)
    {
        List<TbLeave> list = tbLeaveService.selectTbLeaveList(tbLeave);
        ExcelUtil<TbLeave> util = new ExcelUtil<TbLeave>(TbLeave.class);
        util.exportExcel(response, list, "请假示例数据");
    }

    /**
     * 获取请假示例详细信息
     */
    @ApiOperation("获取请假示例详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbLeaveService.selectTbLeaveById(id));
    }

    /**
     * 新增请假示例
     */
    @ApiOperation("新增请假示例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('example:leave:add')")
    @Log(title = "请假示例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbLeave tbLeave)
    {
        return toAjax(tbLeaveService.insertTbLeave(tbLeave));
    }

    /**
     * 修改请假示例
     */
    @ApiOperation("修改请假示例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('example:leave:edit')")
    @Log(title = "请假示例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbLeave tbLeave)
    {
        return toAjax(tbLeaveService.updateTbLeave(tbLeave));
    }

    /**
     * 删除请假示例
     */
    @ApiOperation("删除请假示例")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:leave:remove')")
    @Log(title = "请假示例", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbLeaveService.deleteTbLeaveByIds(ids));
    }

    /**
     * 会签审批
     * @param strData
     * @return
     */
    @PostMapping("/handleMoreP")
    public AjaxResult handleMoreP(@RequestBody String strData)  {
        Map stringToMap =  JSONObject.parseObject(strData);
        return AjaxResult.success(tbLeaveService.handleMoreP(stringToMap));
    }

    /**
     * 经理审批
     * @param strData
     * @return
     */
    @PostMapping("/managerApproval")
    public AjaxResult managerApproval(@RequestBody String strData)  {
        Map stringToMap =  JSONObject.parseObject(strData);
        return AjaxResult.success(tbLeaveService.managerApproval(stringToMap));
    }
    /**
     * 经理审批
     * @param strData
     * @return
     */
    @PostMapping("/personnelApproval")
    public AjaxResult personnelApproval(@RequestBody String strData)  {
         Map stringToMap =  JSONObject.parseObject(strData);
         return AjaxResult.success(tbLeaveService.personnelApproval(stringToMap));
    }

    /**
     * 重新申请
     * @param strData
     * @return
     */
    @PostMapping("/reApply")
    public AjaxResult reApply(@RequestBody String strData)  {
        Map stringToMap =  JSONObject.parseObject(strData);
        return AjaxResult.success(tbLeaveService.reApply(stringToMap));
    }
    /**
     * 撤回申请
     * @param strData
     * @return
     */
    @PostMapping("/rollBackData")
    public AjaxResult rollBackData(@RequestBody String strData)  {
        Map stringToMap =  JSONObject.parseObject(strData);
        return AjaxResult.success(tbLeaveService.rollBackData(stringToMap));
    }
    /**
     * 获取审批历史
     * @param instanceId
     * @return
     */
    @PostMapping("/handleHistory")
    public AjaxResult handleHistory(@RequestBody String instanceId)  {
        return AjaxResult.success(tbLeaveService.handleHistory(instanceId));
    }
    /**
     * 撤回审批
     * @param strData
     * @return
     */
    @PostMapping("/handleReturn")
    public AjaxResult handleReturn(@RequestBody String strData)  {
        Map stringToMap =  JSONObject.parseObject(strData);
        return AjaxResult.success(tbLeaveService.handleReturn(stringToMap));
    }
}
