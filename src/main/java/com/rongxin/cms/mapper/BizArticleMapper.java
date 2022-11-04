package com.rongxin.cms.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rongxin.cms.domain.BizArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 文章内容Mapper接口
 * 
 * @author rx
 * @date 2022-10-09
 */
public interface BizArticleMapper  extends BaseMapper<BizArticle>
{
    /**
     * 查询文章内容
     * 
     * @param id 文章内容主键
     * @return 文章内容
     */
    public BizArticle selectBizArticleById(Long id);

    public List<LinkedHashMap<String,Object>> selectBizArticleAttr(Long id);

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
     * 删除文章内容
     * 
     * @param id 文章内容主键
     * @return 结果
     */
    public int deleteBizArticleById(Long id);

    /**
     * 批量删除文章内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizArticleByIds(Long[] ids);
}
