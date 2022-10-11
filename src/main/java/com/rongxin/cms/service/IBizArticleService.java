package com.rongxin.cms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizArticle;
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
    public Map<String,Object>  selectBizArticleById(Long id);

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
     * @param file 标题图片
     * @param map 数据集合
     * @return 结果
     */
    public int insertBizArticle(MultipartFile[] file, Map<String, Object> map) throws IOException;

    /**
     * 修改文章内容
     * @param file 标题图片
     * @param map 数据集合
     * @return 结果
     */
    public int updateBizArticle(MultipartFile[] file,Map<String, Object> map) throws IOException;

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
}
