package com.rongxin.cms.mapper;

import java.util.List;
import com.rongxin.cms.domain.BizAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 内容拓展属性Mapper接口
 * 
 * @author rx
 * @date 2022-11-01
 */
public interface BizAttributeMapper  extends BaseMapper<BizAttribute>
{
    /**
     * 查询内容拓展属性
     * 
     * @param id 内容拓展属性主键
     * @return 内容拓展属性
     */
    public BizAttribute selectBizAttributeById(Long id);

    /**
     * 查询内容拓展属性列表
     * 
     * @param bizAttribute 内容拓展属性
     * @return 内容拓展属性集合
     */
    public List<BizAttribute> selectBizAttributeList(BizAttribute bizAttribute);

    /**
     * 新增内容拓展属性
     * 
     * @param bizAttribute 内容拓展属性
     * @return 结果
     */
    public int insertBizAttribute(BizAttribute bizAttribute);

    /**
     * 修改内容拓展属性
     * 
     * @param bizAttribute 内容拓展属性
     * @return 结果
     */
    public int updateBizAttribute(BizAttribute bizAttribute);

    /**
     * 删除内容拓展属性
     * 
     * @param id 内容拓展属性主键
     * @return 结果
     */
    public int deleteBizAttributeById(Long id);

    /**
     * 批量删除内容拓展属性
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizAttributeByIds(Long[] ids);
}
