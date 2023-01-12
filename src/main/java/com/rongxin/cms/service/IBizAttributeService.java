package com.rongxin.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizAttribute;

/**
 * 内容拓展属性Service接口
 * 
 * @author rx
 * @date 2022-11-01
 */
public interface IBizAttributeService extends IService<BizAttribute>
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
     * 批量删除内容拓展属性
     * 
     * @param ids 需要删除的内容拓展属性主键集合
     * @return 结果
     */
    public int deleteBizAttributeByIds(Long[] ids);

    /**
     * 删除内容拓展属性信息
     * 
     * @param id 内容拓展属性主键
     * @return 结果
     */
    public int deleteBizAttributeById(Long id);
}
