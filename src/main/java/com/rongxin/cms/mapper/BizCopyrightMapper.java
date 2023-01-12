package com.rongxin.cms.mapper;

import java.util.List;
import com.rongxin.cms.domain.BizCopyright;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * cms版权信息Mapper接口
 * 
 * @author rx
 * @date 2022-10-12
 */
public interface BizCopyrightMapper  extends BaseMapper<BizCopyright>
{
    /**
     * 查询cms版权信息
     * 
     * @param id cms版权信息主键
     * @return cms版权信息
     */
    public BizCopyright selectBizCopyrightById(Long id);

    /**
     * 查询cms版权信息列表
     * 
     * @param bizCopyright cms版权信息
     * @return cms版权信息集合
     */
    public List<BizCopyright> selectBizCopyrightList(BizCopyright bizCopyright);

    /**
     * 新增cms版权信息
     * 
     * @param bizCopyright cms版权信息
     * @return 结果
     */
    public int insertBizCopyright(BizCopyright bizCopyright);

    /**
     * 修改cms版权信息
     * 
     * @param bizCopyright cms版权信息
     * @return 结果
     */
    public int updateBizCopyright(BizCopyright bizCopyright);

    /**
     * 删除cms版权信息
     * 
     * @param id cms版权信息主键
     * @return 结果
     */
    public int deleteBizCopyrightById(Long id);

    /**
     * 批量删除cms版权信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizCopyrightByIds(Long[] ids);
}
