package com.rongxin.cms.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizAttributeValueMapper;
import com.rongxin.cms.domain.BizAttributeValue;
import com.rongxin.cms.service.IBizAttributeValueService;

/**
 * 内容拓展属性值Service业务层处理
 * 
 * @author rx
 * @date 2022-11-01
 */
@Service
public class BizAttributeValueServiceImpl extends ServiceImpl<BizAttributeValueMapper, BizAttributeValue> implements IBizAttributeValueService
{
    @Autowired
    private BizAttributeValueMapper bizAttributeValueMapper;

    /**
     * 查询内容拓展属性值
     * 
     * @param id 内容拓展属性值主键
     * @return 内容拓展属性值
     */
    @Override
    public BizAttributeValue selectBizAttributeValueById(Long id)
    {
        return bizAttributeValueMapper.selectBizAttributeValueById(id);
    }

    /**
     * 查询内容拓展属性值列表
     * 
     * @param bizAttributeValue 内容拓展属性值
     * @return 内容拓展属性值
     */
    @Override
    public List<BizAttributeValue> selectBizAttributeValueList(BizAttributeValue bizAttributeValue)
    {
        return bizAttributeValueMapper.selectBizAttributeValueList(bizAttributeValue);
    }

    /**
     * 新增内容拓展属性值
     * 
     * @param bizAttributeValue 内容拓展属性值
     * @return 结果
     */
    @Override
    public int insertBizAttributeValue(BizAttributeValue bizAttributeValue)
    {
        return bizAttributeValueMapper.insertBizAttributeValue(bizAttributeValue);
    }

    /**
     * 修改内容拓展属性值
     * 
     * @param bizAttributeValue 内容拓展属性值
     * @return 结果
     */
    @Override
    public int updateBizAttributeValue(BizAttributeValue bizAttributeValue)
    {
        return bizAttributeValueMapper.updateBizAttributeValue(bizAttributeValue);
    }

    /**
     * 批量删除内容拓展属性值
     * 
     * @param ids 需要删除的内容拓展属性值主键
     * @return 结果
     */
    @Override
    public int deleteBizAttributeValueByIds(Long[] ids)
    {
        return bizAttributeValueMapper.deleteBizAttributeValueByIds(ids);
    }

    /**
     * 删除内容拓展属性值信息
     * 
     * @param id 内容拓展属性值主键
     * @return 结果
     */
    @Override
    public int deleteBizAttributeValueById(Long id)
    {
        return bizAttributeValueMapper.deleteBizAttributeValueById(id);
    }
}
