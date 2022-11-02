package com.rongxin.cms.mapper;

import java.util.List;
import com.rongxin.cms.domain.BizAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 内容拓展属性值Mapper接口
 * 
 * @author rx
 * @date 2022-11-01
 */
public interface BizAttributeValueMapper  extends BaseMapper<BizAttributeValue>
{
    /**
     * 查询内容拓展属性值
     * 
     * @param id 内容拓展属性值主键
     * @return 内容拓展属性值
     */
    public BizAttributeValue selectBizAttributeValueById(Long id);

    /**
     * 查询内容拓展属性值列表
     * 
     * @param bizAttributeValue 内容拓展属性值
     * @return 内容拓展属性值集合
     */
    public List<BizAttributeValue> selectBizAttributeValueList(BizAttributeValue bizAttributeValue);

    /**
     * 新增内容拓展属性值
     * 
     * @param bizAttributeValue 内容拓展属性值
     * @return 结果
     */
    public int insertBizAttributeValue(BizAttributeValue bizAttributeValue);

    /**
     * 修改内容拓展属性值
     * 
     * @param bizAttributeValue 内容拓展属性值
     * @return 结果
     */
    public int updateBizAttributeValue(BizAttributeValue bizAttributeValue);

    /**
     * 删除内容拓展属性值
     * 
     * @param id 内容拓展属性值主键
     * @return 结果
     */
    public int deleteBizAttributeValueById(Long id);

    /**
     * 批量删除内容拓展属性值
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizAttributeValueByIds(Long[] ids);
}
