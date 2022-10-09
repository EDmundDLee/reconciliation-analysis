package com.rongxin.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizArticle;

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
    public BizArticle selectBizArticleById(Long id);

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
     * @param bizArticle 文章内容
     * @return 结果
     */
    public int insertBizArticle(BizArticle bizArticle);

    /**
     * 修改文章内容
     * 
     * @param bizArticle 文章内容
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
}
