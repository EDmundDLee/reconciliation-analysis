package com.rongxin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongxin.cms.domain.BizLink;

import java.util.List;

/**
 * Mapper接口
 * 
 * @author rx
 * @date 2022-10-09
 */
public interface BizLinkMapper extends BaseMapper<BizLink>
{
    /**
     * 查询
     * 
     * @param id 主键
     * @return 
     */
    public BizLink selectBizLinkById(Long id);

    /**
     * 查询列表
     * 
     * @param bizLink 
     * @return 集合
     */
    public List<BizLink> selectBizLinkList(BizLink bizLink);

    /**
     * 新增
     * 
     * @param bizLink 
     * @return 结果
     */
    public int insertBizLink(BizLink bizLink);

    /**
     * 修改
     * 
     * @param bizLink 
     * @return 结果
     */
    public int updateBizLink(BizLink bizLink);

    /**
     * 删除
     * 
     * @param id 主键
     * @return 结果
     */
    public int deleteBizLinkById(Long id);

    /**
     * 批量删除
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizLinkByIds(Long[] ids);
    
    /**
     * 逻辑删除
     * 
     * @param id 需要删除的数据主键集合
     * @return 结果
     */
    public int updateIsDel(Long id);
}
