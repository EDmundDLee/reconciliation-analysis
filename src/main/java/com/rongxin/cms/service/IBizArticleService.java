package com.rongxin.cms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizArticle;
import com.rongxin.cms.domain.BizPicture;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章内容Service接口
 * 
 * @author rx
 * @date 2022-10-09
 */
public interface IBizArticleService extends IService<BizArticle>
{
    /**
     * 查询文章内容
     * 
     * @param id 文章内容主键
     * @return 文章内容
     */
    public BizArticle  selectBizArticleById(Long id);
    /**
     * 查询文章内容及规则属性
     *
     * @param id 文章内容主键
     * @return 文章内容
     */
    public Map<String,Object>  selectBizArticleAndAttrById(Long id);


    /**
     * 查询文章内容
     *
     * @param id 文章内容主键
     * @return 文章内容
     */
    public  List<BizPicture> getPictureInfo(Long id);
    /**
     * 查询文章内容列表
     * 
     * @param bizArticle 文章内容
     * @return 文章内容集合
     */
    public List<BizArticle> selectBizArticleList(BizArticle bizArticle);

    /**
     * 新增文章内容
     * 
     * @param bizArticle 内容对象
     * @return 结果
     */
    public int insertBizArticle(BizArticle bizArticle);

    /**
     * 修改文章内容
     * @param bizArticle 内容对象
     * @return 结果
     */
    public int updateBizArticle(BizArticle bizArticle);

    /**
     * 批量删除文章内容
     * 
     * @param ids 需要删除的文章内容主键集合
     * @return 结果
     */
    public int deleteBizArticleByIds(Long[] ids);

    /**
     * 删除文章内容信息
     * 
     * @param id 文章内容主键
     * @return 结果
     */
    public int deleteBizArticleById(Long id);
    /**
     * 上传图片信息
     *
     * @param file 图片信息
     * @return 结果
     */
    public BizPicture uploadPic(MultipartFile file,Long id) throws IOException;

    /**
     * 删除图片信息
     *
     * @param bizPicture 图片对象
     * @return 结果
     */
    public int deletePictureInfo(BizPicture bizPicture);

    public Map<String,Object> getRuleAttr();
    /**
     * 绑定规则
     *
     * @return 结果
     */
    public int bindRule(Map<String,Object> map);
}
