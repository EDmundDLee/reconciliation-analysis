package com.rongxin.demo.controller;

import java.io.IOException;
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
import com.rongxin.demo.domain.RxStudent;
import com.rongxin.demo.service.IRxStudentService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;

/**
 * allcontentController
 * 
 * @author rx
 * @date 2022-09-06
 */
@RestController
@RequestMapping("/allcontent/student")
public class RxStudentController extends BaseController
{
    @Autowired
    private IRxStudentService rxStudentService;

    /**
     * 全文检索列表
     */
    @ApiOperation("全文检索列表")
    @PreAuthorize("@ss.hasPermi('allcontent:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(RxStudent rxStudent) throws IOException {
        startPage();
        List<RxStudent> list = rxStudentService.selectRxStudentList(rxStudent);
        return getDataTable(list);
    }

    /**
     * 导出全文检索列表
     */
    @ApiOperation("导出全文检索列表")
    @PreAuthorize("@ss.hasPermi('allcontent:student:export')")
    @Log(title = "全文检索", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RxStudent rxStudent) throws IOException {
        List<RxStudent> list = rxStudentService.selectRxStudentList(rxStudent);
        ExcelUtil<RxStudent> util = new ExcelUtil<RxStudent>(RxStudent.class);
        util.exportExcel(response, list, "全文检索数据");
    }

    /**
     * 获取全文检索详细信息
     */
    @ApiOperation("获取全文检索详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('allcontent:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) throws IOException {
        return AjaxResult.success(rxStudentService.selectRxStudentById(id));
    }

    /**
     * 新增学生
     */
    @ApiOperation("新增学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "String", dataTypeClass = String.class)
    })
    @PreAuthorize("@ss.hasPermi('allcontent:student:add')")
    @Log(title = "全文检索", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RxStudent rxStudent) throws IOException {
        return AjaxResult.success(rxStudentService.insertRxStudent(rxStudent));
    }

    /**
     * 修改allcontent
     */
    @ApiOperation("修改全文检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", dataType = "Integer", dataTypeClass = String.class)
    })
    @PreAuthorize("@ss.hasPermi('allcontent:student:edit')")
    @Log(title = "全文检索", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RxStudent rxStudent) throws IOException {
        return AjaxResult.success(rxStudentService.updateRxStudent(rxStudent));
    }

    /**
     * 删除allcontent
     */
    @ApiOperation("删除全文检索")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String", paramType = "path", dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('allcontent:student:remove')")
    @Log(title = "全文检索", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) throws IOException {
        return toAjax(rxStudentService.deleteRxStudentByIds(ids));
    }
}
