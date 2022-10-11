package com.rongxin.cms.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.BizPicture;
import com.rongxin.cms.mapper.BizPictureMapper;
import com.rongxin.common.utils.DateUtils;
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
    public  BizArticle selectBizArticleById(Long id)
    {
        return bizArticleMapper.selectBizArticleById(id);
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
        return bizArticleMapper.insertBizArticle(bizArticle);
    }
    /**
     * 修改文章内容
     * @param bizArticle 内容对象
     * @return 结果
     */
    @Override
    public int updateBizArticle(BizArticle bizArticle)
    {
        return bizArticleMapper.updateBizArticle(bizArticle);
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
}
