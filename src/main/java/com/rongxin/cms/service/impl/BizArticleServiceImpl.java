package com.rongxin.cms.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.*;
import com.rongxin.cms.mapper.*;
import com.rongxin.cms.service.IBizAttributeValueService;
import com.rongxin.common.utils.DateUtils;
import com.rongxin.web.framework.web.service.ISysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private IBizAttributeValueService bizAttributeValueService;
    /**
     * 查询文章内容
     * 
     * @param id 文章内容主键
     * @return 文章内容
     */
    @Override
    public  BizArticle selectBizArticleById(Long id)
    {
         return bizArticleMapper.selectBizArticleById(id);
    }
    /**
     * 查询文章内容及规则属性
     *
     * @param id 文章内容主键
     * @return 文章内容
     */
    @Override
    public  Map<String,Object> selectBizArticleAndAttrById(Long id)
    {
        Map<String,Object> map = new HashMap<>();
        BizArticle bizArticle = bizArticleMapper.selectBizArticleById(id);
        map.put("attrList",bizArticleMapper.selectBizArticleAttr(id,bizArticle.getColumnId()));
        map.put("articleList",bizArticle);
        return map;
    }
    /**
     * 查询文章图片信息内容
     *
     * @param id 文章内容主键
     * @return 图片
     */
    @Override
    public  List<BizPicture> getPictureInfo(Long id)
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", id);
        return bizPictureMapper.selectList(queryWrapper);
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
     * @param bizArticle 内容对象
     * @return 结果
     */
    @Override
    public int insertBizArticle(BizArticle bizArticle)
    {

        int flag = 0;
        if(bizArticleMapper.insertBizArticle(bizArticle)>0){
            flag = 1;
            if(bizArticle.getParams()!=null) {
                saveOrUpdateBizAAttributeValue(bizArticle);
            }
        }
        return flag;
    }
    private void saveOrUpdateBizAAttributeValue(BizArticle bizArticle){
        Map<String,Object> map =  bizArticle.getParams();
        Map<String,Object> mapValue = new HashMap<>();
        ArrayList list = (ArrayList) map.get("params");
        BizAttributeValue bizAttributeValue = null;
        for(int i=0;i<list.size();i++){
            mapValue =  (Map<String,Object>)list.get(i);
            bizAttributeValue = new BizAttributeValue();
            bizAttributeValue.setColumnId(bizArticle.getColumnId());
            bizAttributeValue.setArticleId(bizArticle.getId());
            bizAttributeValue.setRuleId(Long.valueOf(String.valueOf(mapValue.get("rule_id"))));
            bizAttributeValue.setAttrId(Long.valueOf(String.valueOf(mapValue.get("attrId"))));
            bizAttributeValue.setAttrIndex( mapValue.get("attr_index")==null?"":String.valueOf(mapValue.get("attr_index")));
            bizAttributeValue.setAttrOrder( mapValue.get("attr_order")==null?"":String.valueOf(mapValue.get("attr_order")));
            bizAttributeValue.setAttrId(Long.valueOf(String.valueOf(mapValue.get("attrId"))));
            bizAttributeValue.setAttrValue( mapValue.get("attrValue")==null?"":String.valueOf(mapValue.get("attrValue")));
            bizAttributeValue.setId(mapValue.get("id")==null?null: Long.valueOf(String.valueOf( mapValue.get("id"))));
            if(bizAttributeValue.getId()!=null){
                bizAttributeValueService.updateBizAttributeValue(bizAttributeValue);
            }else{
                bizAttributeValueService.insertBizAttributeValue(bizAttributeValue);
            }
        }
    }
    /**
     * 修改文章内容
     * @param bizArticle 内容对象
     * @return 结果
     */
    @Override
    public int updateBizArticle(BizArticle bizArticle)
    {
        int flag = 0;
        if(bizArticleMapper.updateBizArticle(bizArticle)>0){
            flag = 1;
            if(bizArticle.getParams()!=null){
                saveOrUpdateBizAAttributeValue(bizArticle);
            }
        }
        return flag;
    }

    private String uploadPicture(MultipartFile file) throws IOException {
        if(file != null){
            String fName = file.getOriginalFilename();
            // 上传文件路径
            String filePath = sysOssService.getUploadPath();
            // 上传并返回新文件名称
            return sysOssService.upload(file, fName,filePath);
        }else{
            return "";
        }
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


    /**
     * 上传图片
     * @param file 图片信息
     * @return
     * @throws IOException
     */
    @Override
    public BizPicture uploadPic(MultipartFile file,Long id) throws IOException {
        String url = uploadPicture(file);
        BizArticle bizArticle =  bizArticleMapper.selectBizArticleById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", bizArticle.getId());
        List<BizPicture> list = bizPictureMapper.selectList(queryWrapper);
        BizPicture bizPicture =  new BizPicture();
        bizPicture.setUrl(url);
        bizPicture.setArticleId(id);
        bizPictureMapper.insert(bizPicture);
        if(list==null || list.size()==0 ){
            bizArticle.setTitleImgUrl(url);
            bizArticle.setTitleImgId(bizPicture.getId());
            bizArticleMapper.updateBizArticle(bizArticle);
        }
        return bizPicture;
    }

    @Override
    public int deletePictureInfo(BizPicture bizPicture) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("article_id", bizPicture.getArticleId());
        List<BizPicture> list = bizPictureMapper.selectList(queryWrapper);
        int flag = bizPictureMapper.deleteById(bizPicture.getId());
        if(list != null && list.size() == 1){
            BizArticle bizArticle = bizArticleMapper.selectBizArticleById(bizPicture.getArticleId());
            bizArticle.setTitleImgUrl("");
            bizArticle.setTitleImgId(null);
            bizArticleMapper.updateBizArticle(bizArticle);
        }
        return flag;
    }

    @Override
    public Map<String, Object> getArticleAttrByColumnId(Long columnId) {
        Map<String,Object> map = new HashMap<>();
        map.put("attrList",bizArticleMapper.getArticleAttrByColumnId(columnId));
         return map;
    }

}
