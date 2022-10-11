package com.rongxin.cms.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.BizPicture;
import com.rongxin.cms.mapper.BizPictureMapper;
import com.rongxin.common.utils.DateUtils;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.web.framework.web.service.ISysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizArticleMapper;
import com.rongxin.cms.domain.BizArticle;
import com.rongxin.cms.service.IBizArticleService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章内容Service业务层处理
 * 
 * @author rx
 * @date 2022-10-09
 */
@Service
public class BizArticleServiceImpl extends ServiceImpl<BizArticleMapper, BizArticle> implements IBizArticleService
{
    @Autowired
    private BizArticleMapper bizArticleMapper;
    @Autowired
    private ISysOssService sysOssService;

    @Autowired
    private BizPictureMapper bizPictureMapper;
    /**
     * 查询文章内容
     * 
     * @param id 文章内容主键
     * @return 文章内容
     */
    @Override
    public  Map<String,Object>  selectBizArticleById(Long id)
    {
        Map<String,Object> map = new HashMap<>();
        BizArticle bizArticle = bizArticleMapper.selectBizArticleById(id);
        map.put("article",bizArticle);
        map.put("pictures",bizArticleMapper.selectBizArticleById(id));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", bizArticle.getId());
        map.put("pictures", bizPictureMapper.selectList(queryWrapper));
        return map;
    }

    /**
     * 查询文章内容列表
     * 
     * @param bizArticle 文章内容
     * @return 文章内容
     */
    @Override
    public List<BizArticle> selectBizArticleList(BizArticle bizArticle)
    {
        return bizArticleMapper.selectBizArticleList(bizArticle);
    }

    /**
     * 新增文章内容
     * @param file 标题图片
     * @param map 数据集合
     * @return 结果
     */
    @Override
    public int insertBizArticle(MultipartFile[] file,Map<String, Object> map) throws IOException {
        String pathUrl = "";

        if(file != null && file.length>0){
            pathUrl = uploadPicture(file[0]);//默认第一张为展示图
        }else{
            pathUrl = "";
        }
        BizArticle bizArticle =  getBizArticle(map,pathUrl);
        bizArticle.setCreateTime(DateUtils.getNowDate());
        bizArticle.setCreateName(SecurityUtils.getUsername());
        int flag = bizArticleMapper.insertBizArticle(bizArticle);
        if(file != null && file.length>0){
            //处理子数据
            BizPicture bizPicture = new BizPicture();
            bizPicture.setArticleId(bizArticle.getId());
            bizPicture.setUrl(bizArticle.getTitleImgUrl());
            bizPictureMapper.insert(bizPicture);
            for(int i=1;i<file.length;i++){
                bizPicture = new BizPicture();
                bizPicture.setArticleId(bizArticle.getId());
                bizPicture.setUrl(uploadPicture(file[i]));
                bizPictureMapper.insert(bizPicture);
            }
        }
        return flag;
    }

    /**
     * 修改文章内容
     * @param file 标题图片
     * @param map 数据集合
     * @return 结果
     */
    @Override
    public int updateBizArticle(MultipartFile[] file, Map<String, Object> map) throws IOException {
        String pathUrl = "";
        BizArticle bizArticle = null;
        if(file == null){
               bizArticle =  getBizArticle(map,"");
         }else{
             if(file.length>0){
                 pathUrl = uploadPicture(file[0]);
             }
               bizArticle =  getBizArticle(map,pathUrl);
             bizArticle.setCreateName(SecurityUtils.getUsername());

             QueryWrapper queryWrapper = new QueryWrapper();
             queryWrapper.eq("article_id", bizArticle.getId());
             //处理子数据
             bizPictureMapper.delete(queryWrapper);//先删除在新增
             BizPicture bizPicture = new BizPicture();
             bizPicture.setArticleId(bizArticle.getId());
             bizPicture.setUrl(bizArticle.getTitleImgUrl());
             bizPictureMapper.insert(bizPicture);
             for(int i=1;i<file.length;i++){
                 bizPicture = new BizPicture();
                 bizPicture.setArticleId(bizArticle.getId());
                 bizPicture.setUrl(uploadPicture(file[i]));
                 bizPictureMapper.insert(bizPicture);
             }
         }
        return bizArticleMapper.updateBizArticle(bizArticle);
    }
    private String uploadPicture(MultipartFile file) throws IOException {
        String fName = file.getOriginalFilename();
        // 上传文件路径
        String filePath = sysOssService.getUploadPath();
        // 上传并返回新文件名称
        return sysOssService.upload(file, fName,filePath);
    }
    /**
     * 获取主信息对象
     * @param map  数据集合
     * @param url 标题图片
     * @return
     */
    private BizArticle getBizArticle(Map<String, Object> map,String url ){
        String str = map.get("formData")!=null ?map.get("formData").toString():"";
        BizArticle bizArticle = new BizArticle();
        if(!"".equals(str)){
            bizArticle =  JSONObject.parseObject(str, BizArticle.class);
            bizArticle.setUpdateDate(DateUtils.getNowDate());
            bizArticle.setUpdateTime(DateUtils.getNowDate());
            bizArticle.setTitleImgUrl(url);
        }
        return bizArticle;
    }

    /**
     * 批量删除文章内容
     * 
     * @param ids 需要删除的文章内容主键
     * @return 结果
     */
    @Override
    public int deleteBizArticleByIds(Long[] ids)
    {
        return bizArticleMapper.deleteBizArticleByIds(ids);
    }

    /**
     * 删除文章内容信息
     * 
     * @param id 文章内容主键
     * @return 结果
     */
    @Override
    public int deleteBizArticleById(Long id)
    {
        return bizArticleMapper.deleteBizArticleById(id);
    }
}
