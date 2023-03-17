package com.rongxin.web.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongxin.web.domain.BusinessDict;

/**
 * 业务字典Mapper接口
 * 
 * @author rx
 * @date 2023-03-14
 */
public interface BusinessDictMapper  extends BaseMapper<BusinessDict>
{
    /**
     * 查询业务字典
     * 
     * @param id 业务字典主键
     * @return 业务字典
     */
    public BusinessDict selectBusinessDictById(Long id);
    /**
     * 根据字典id当前字典下所有子数据
     *
     * @param id 业务字典主键
     * @return 业务字典
     */
    public List<BusinessDict> selectBusinessDictListOfAllSon(Long id);
    /**
     * 根据字典id当前字典下所有父节点数据
     *
     * @param id 业务字典主键
     * @return 业务字典
     */
    public List<BusinessDict> selectBusinessDictListOfAllParent(Long id);
    /**
     * 查询业务字典列表
     * 
     * @param businessDict 业务字典
     * @return 业务字典集合
     */
    public List<BusinessDict> selectBusinessDictList(BusinessDict businessDict);

    /**
     * 新增业务字典
     * 
     * @param businessDict 业务字典
     * @return 结果
     */
    public int insertBusinessDict(BusinessDict businessDict);

    /**
     * 修改业务字典
     * 
     * @param businessDict 业务字典
     * @return 结果
     */
    public int updateBusinessDict(BusinessDict businessDict);

    /**
     * 删除业务字典
     * 
     * @param id 业务字典主键
     * @return 结果
     */
    public int deleteBusinessDictById(Long id);

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusinessDictByIds(Long[] ids);
}
