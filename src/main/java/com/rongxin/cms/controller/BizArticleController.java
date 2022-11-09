package com.rongxin.cms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rongxin.cms.domain.BizColumnTree;
import com.rongxin.cms.domain.BizPicture;
import com.rongxin.cms.service.impl.BizColumnServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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
        return AjaxResult.success(bizArticleService.selectBizArticleAndAttrById(id));
    }
    /**
     * 获取文章标题图片信息
     */
    @ApiOperation("获取文章标题图片信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:article:query')")
    @GetMapping(value = "/getPictureInfo")
    public AjaxResult getPictureInfo(Long id)
    {
        return AjaxResult.success(bizArticleService.getPictureInfo(id));
    }
    /**
     * 新增文章内容
     */
    @ApiOperation("新增文章内容")
    @PreAuthorize("@ss.hasPermi('cms:article:add')")
    @Log(title = "文章内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizArticle bizArticle)
    {
        return toAjax(bizArticleService.insertBizArticle(bizArticle));
    }

    /**
     * 修改文章内容
     */
    @ApiOperation("修改文章内容")
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "文章内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizArticle bizArticle)
    {
        return toAjax(bizArticleService.updateBizArticle(bizArticle));
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
       List<BizColumnTree> cols = bizColumnService.selectColList();
       return AjaxResult.success(bizColumnService.buildColTreeSelect(cols));
    }

    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "上传图片", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadPic")
    public AjaxResult uploadPic(MultipartFile file,Long id) throws IOException {
        return AjaxResult.success(bizArticleService.uploadPic(file,id));
    }

    @ApiOperation("删除图片信息")
    @PreAuthorize("@ss.hasPermi('cms:article:edit')")
    @Log(title = "删除图片信息", businessType = BusinessType.DELETE)
    @PostMapping("/deletePictureInfo")
    public AjaxResult deletePictureInfo(@RequestBody BizPicture bizPicture)
    {
        return toAjax(bizArticleService.deletePictureInfo(bizPicture));
    }
    /**
     * 获取文章内容详细信息
     */
    @ApiOperation("获取文章内容详细信息")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermi('cms:article:query')")
    @GetMapping(value = "/getArticleAttr/{id}")
    public AjaxResult getArticleAttr(@PathVariable("id") Long columnId)
    {
        return AjaxResult.success(bizArticleService.getArticleAttrByColumnId(columnId));
    }

}
