package com.rongxin.cms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rongxin.cms.domain.BizColumn;
import com.rongxin.cms.service.impl.BizColumnServiceImpl;
import com.rongxin.common.core.domain.entity.SysDept;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.cms.domain.BizArticle;
import com.rongxin.cms.service.IBizArticleService;
import com.rongxin.common.utils.poi.ExcelUtil;
import com.rongxin.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章内容Controller
 * 
 * @author rx
 * @date 2022-10-09
 */
@RestController
@RequestMapping("/cms/article")
public class BizArticleController extends BaseController
{
    @Autowired
    private IBizArticleService bizArticleService;
    @Autowired
    private BizColumnServiceImpl bizColumnService;


    /**
     * 查询文章内容列表
     */
    @ApiOperation("查询文章内容列表")
    @PreAuthorize("@ss.hasPermi('cms:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizArticle bizArticle)
    {
        startPage();
        List<BizArticle> list = bizArticleService.selectBizArticleList(bizArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章内容列表
     */
    @ApiOperation("导出文章内容列表")
    @PreAuthorize("@ss.hasPermi('cms:article:export')")
    @Log(title = "文章内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizArticle bizArticle)
    {
        List<BizArticle> list = bizArticleService.selectBizArticleList(bizArticle);
        ExcelUtil<BizArticle> util = new ExcelUtil<BizArticle>(BizArticle.class);
        util.exportExcel(response, list, "文章内容数据");
    }

    /**
     * 获取文章内容详细信息
     */
    @ApiOperation("获取文章内容详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizArticleService.selectBizArticleById(id));
    }

    /**
     * 新增文章内容
     */
    @ApiOperation("新增文章内容")
    @PreAuthorize("@ss.hasPermi('cms:article:add')")
    @Log(title = "文章内容", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestParam(value="files" , required = false) MultipartFile[] files,
                          HttpServletRequest request,
                          @RequestParam Map<String, Object> map) throws IOException {
        return toAjax(bizArticleService.insertBizArticle(files,map));
    }

    /**
     * 修改文章内容
     */
    @ApiOperation("修改文章内容")
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "文章内容", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestParam(value="files" , required = false)MultipartFile[] files,
                           HttpServletRequest request,
                           @RequestParam Map<String, Object> map) throws IOException {
        return toAjax(bizArticleService.updateBizArticle(files,map));
    }

    /**
     * 删除文章内容
     */
    @ApiOperation("删除文章内容")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:article:remove')")
    @Log(title = "文章内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizArticleService.deleteBizArticleByIds(ids));
    }


    /**
     * 获取栏目类别下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect()
    {
       List<BizColumn> cols = bizColumnService.selectColList();
       return AjaxResult.success(bizColumnService.buildColTreeSelect(cols));
    }
}
