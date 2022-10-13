package com.rongxin.cms.controller;

import com.rongxin.cms.domain.BizArticle;
import com.rongxin.cms.domain.BizColumn;
import com.rongxin.cms.domain.BizLink;
import com.rongxin.cms.domain.BizPicture;
import com.rongxin.cms.service.*;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.page.TableDataInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 网站接口集合
 * 
 * @author rx
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/cms/web")
public class BizWebController extends BaseController
{

    @Autowired
    private IBizArticleService bizArticleService;
    @Autowired
    private IBizColumnService bizColumnService;
    @Autowired
    private IBizLinkService bizLinkService;
    @Autowired
    private IBizPictureService bizPictureService;
    @Autowired
    private IBizWebService bizWebService;
    /**
     * 查询文章内容列表
     */
    @ApiOperation("查询文章内容列表")
    @GetMapping("/artcileList")
    public TableDataInfo list(BizArticle bizArticle)
    {
        startPage();
        List<BizArticle> list = bizArticleService.selectBizArticleList(bizArticle);
        return getDataTable(list);
    }
    /**
     * 查询栏目类别列表
     */
    @ApiOperation("查询栏目类别列表")
    @GetMapping("/columnList")
    public AjaxResult list(BizColumn bizColumn)
    {
        List<BizColumn> list = bizColumnService.selectBizColumnList(bizColumn);
        return AjaxResult.success(list);
    }
    /**
     * 查询列表
     */
    @ApiOperation("查询连接列表")
    @GetMapping("/linkList")
    public TableDataInfo list(BizLink bizLink)
    {
        startPage();
        List<BizLink> list = bizLinkService.selectBizLinkList(bizLink);
        return getDataTable(list);
    }
    /**
     * 查询图片信息列表
     */
    @ApiOperation("查询图片列表")
    @GetMapping("/pictureList")
    public TableDataInfo list(BizPicture bizPicture)
    {
        startPage();
        List<BizPicture> list = bizPictureService.selectBizPictureList(bizPicture);
        return getDataTable(list);
    }
    /**
     * 获取文章内容详细信息
     */
    @ApiOperation("获取文章内容详细信息")
    @GetMapping(value = "/article/{id}")
    public AjaxResult articleGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizArticleService.selectBizArticleById(id));
    }

    /**
     * 获取栏目类别详细信息
     */
    @ApiOperation("获取栏目类别详细信息")
    @GetMapping(value = "/column/{id}")
    public AjaxResult columnGetInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bizColumnService.selectBizColumnById(id));
    }
    /**
     * 全文检索
     */
    @ApiOperation("全文检索")
    @GetMapping("/search")
    public AjaxResult search(@RequestParam Map<String, Object> params) throws Exception {
         return AjaxResult.success(bizWebService.search(params));
    }
}
