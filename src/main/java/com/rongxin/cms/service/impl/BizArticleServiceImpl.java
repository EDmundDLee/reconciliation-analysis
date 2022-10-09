package com.rongxin.cms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizArticleMapper;
import com.rongxin.cms.domain.BizArticle;
import com.rongxin.cms.service.IBizArticleService;

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

    /**
     * 查询文章内容
     * 
     * @param id 文章内容主键
     * @return 文章内容
     */
    @Override
    public BizArticle selectBizArticleById(Long id)
    {
        return bizArticleMapper.selectBizArticleById(id);
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
     * 
     * @param bizArticle 文章内容
     * @return 结果
     */
    @Override
    public int insertBizArticle(BizArticle bizArticle)
    {
        return bizArticleMapper.insertBizArticle(bizArticle);
    }

    /**
     * 修改文章内容
     * 
     * @param bizArticle 文章内容
     * @return 结果
     */
    @Override
    public int updateBizArticle(BizArticle bizArticle)
    {
        return bizArticleMapper.updateBizArticle(bizArticle);
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
