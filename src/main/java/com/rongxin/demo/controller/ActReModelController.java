package com.rongxin.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
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
import com.rongxin.demo.domain.ActReModel;
import com.rongxin.demo.service.IActReModelService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * 工作流基础示例Controller
 * 
 * @author rx
 * @date 2022-09-14
 */
@RestController
@RequestMapping("/example/model")
public class ActReModelController extends BaseController
{
    @Autowired
    private IActReModelService actReModelService;

    /**
     * 部署流程（将resource下的bpmn和png文件部署到数据库中，
     * 本项目中由于采用的事原生设计器可不考虑，如果没有通过设计器而是文件这采用此手段）
     */
    @PostMapping("/deployByFile")
    public void deployByFile()
    {
       actReModelService.deployment();
    }

    /**
     * 通过创建的model 名称 获取当前的任务信息（需要启动一次后才能获取到数据）
     * @param keyName
     * @return
     */
    @PostMapping("/getAllProcessByKeyName")
    public AjaxResult getAllProcessByKeyName(@RequestBody String keyName)  {
        return AjaxResult.success(actReModelService.getAllProcessByKeyName(keyName));
    }

    /**
     * 通过创建的model 名称 获取流程key 并启动当前key 的流程
     * @param keyName
     * @return
     */
    @PostMapping("/startProcess")
    public AjaxResult startProcess(@RequestBody String keyName){
        return AjaxResult.success(actReModelService.startProcess(keyName));
    }

    /**
     * 提出申请
     * @param keyName
     * @return
     */
    @PostMapping("/applyProcess")
    public AjaxResult applyProcess(@RequestBody String keyName) {
        return AjaxResult.success(actReModelService.applyProcess(keyName));
    }

    /**
     * 启动后 可以对当前启动的流程 进行挂起或者解挂
     * @param modelId
     * @return
     */
    @PostMapping("/handUpOrDownDeployment")
    public AjaxResult handUpOrDownDeployment(@RequestBody String modelId) {
        return AjaxResult.success(actReModelService.handUpOrDownDeployment(modelId));
    }

    /**
     * 删除发布  如果启动了 则由于外键关系无法删除
     * @param modelId
     * @return
     */
    @PostMapping("/deleteDeployment")
    public AjaxResult deleteDeployment(@RequestBody String modelId)  {
        return AjaxResult.success(actReModelService.deleteDeployment(modelId));
    }

    /**
     * 查询工作流基础示例列表
     */
    @ApiOperation("查询工作流基础示例列表")
    @PreAuthorize("@ss.hasPermi('example:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActReModel actReModel)
    {
        startPage();
        List<ActReModel> list = actReModelService.selectActReModelList(actReModel);
        return getDataTable(list);
    }

    /**
     * 导出工作流基础示例列表
     */
    @ApiOperation("导出工作流基础示例列表")
    @PreAuthorize("@ss.hasPermi('example:model:export')")
    @Log(title = "工作流基础示例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActReModel actReModel)
    {
        List<ActReModel> list = actReModelService.selectActReModelList(actReModel);
        ExcelUtil<ActReModel> util = new ExcelUtil<ActReModel>(ActReModel.class);
        util.exportExcel(response, list, "工作流基础示例数据");
    }

    /**
     * 获取工作流基础示例详细信息
     */
    @ApiOperation("获取工作流基础示例详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:model:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(actReModelService.selectActReModelById(id));
    }

    /**
     * 新增工作流基础示例
     */
    @ApiOperation("新增工作流基础示例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('example:model:add')")
    @Log(title = "工作流基础示例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActReModel actReModel) throws UnsupportedEncodingException {
        return toAjax(actReModelService.insertActReModel(actReModel));
    }

    /**
     * 修改工作流基础示例
     */
    @ApiOperation("修改工作流基础示例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermi('example:model:edit')")
    @Log(title = "工作流基础示例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActReModel actReModel)
    {
        return toAjax(actReModelService.updateActReModel(actReModel));
    }

    /**
     * 删除工作流基础示例
     */
    @ApiOperation("删除工作流基础示例")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:model:remove')")
    @Log(title = "工作流基础示例", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(actReModelService.deleteActReModelByIds(ids));
    }
    /**
     * 发布工作流基础示例
     */
    @ApiOperation("发布工作流基础示例")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('example:model:deploy')")
    @Log(title = "工作流基础示例", businessType = BusinessType.DELETE)
    @PostMapping("/deploy")
    public AjaxResult deploy(@RequestBody String modelId) throws IOException {
        return AjaxResult.success(actReModelService.deployActReModel(modelId));
    }
}
