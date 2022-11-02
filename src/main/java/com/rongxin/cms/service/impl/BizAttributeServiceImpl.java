package com.rongxin.cms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizAttributeMapper;
import com.rongxin.cms.domain.BizAttribute;
import com.rongxin.cms.service.IBizAttributeService;

/**
 * 内容拓展属性Service业务层处理
 * 
 * @author rx
 * @date 2022-11-01
 */
@Service
public class BizAttributeServiceImpl extends ServiceImpl<BizAttributeMapper, BizAttribute> implements IBizAttributeService
{
    @Autowired
    private BizAttributeMapper bizAttributeMapper;

    /**
     * 查询内容拓展属性
     * 
     * @param id 内容拓展属性主键
     * @return 内容拓展属性
     */
    @Override
    public BizAttribute selectBizAttributeById(Long id)
    {
        return bizAttributeMapper.selectBizAttributeById(id);
    }

    /**
     * 查询内容拓展属性列表
     * 
     * @param bizAttribute 内容拓展属性
     * @return 内容拓展属性
     */
    @Override
    public List<BizAttribute> selectBizAttributeList(BizAttribute bizAttribute)
    {
        return bizAttributeMapper.selectBizAttributeList(bizAttribute);
    }

    /**
     * 新增内容拓展属性
     * 
     * @param bizAttribute 内容拓展属性
     * @return 结果
     */
    @Override
    public int insertBizAttribute(BizAttribute bizAttribute)
    {
        return bizAttributeMapper.insertBizAttribute(bizAttribute);
    }

    /**
     * 修改内容拓展属性
     * 
     * @param bizAttribute 内容拓展属性
     * @return 结果
     */
    @Override
    public int updateBizAttribute(BizAttribute bizAttribute)
    {
        return bizAttributeMapper.updateBizAttribute(bizAttribute);
    }

    /**
     * 批量删除内容拓展属性
     * 
     * @param ids 需要删除的内容拓展属性主键
     * @return 结果
     */
    @Override
    public int deleteBizAttributeByIds(Long[] ids)
    {
        return bizAttributeMapper.deleteBizAttributeByIds(ids);
    }

    /**
     * 删除内容拓展属性信息
     * 
     * @param id 内容拓展属性主键
     * @return 结果
     */
    @Override
    public int deleteBizAttributeById(Long id)
    {
        return bizAttributeMapper.deleteBizAttributeById(id);
    }
}
