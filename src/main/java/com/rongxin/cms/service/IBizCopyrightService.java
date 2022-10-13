package com.rongxin.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizCopyright;

/**
 * cms版权信息Service接口
 * 
 * @author rx
 * @date 2022-10-12
 */
public interface IBizCopyrightService extends IService<BizCopyright>
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
     * 批量删除cms版权信息
     * 
     * @param ids 需要删除的cms版权信息主键集合
     * @return 结果
     */
    public int deleteBizCopyrightByIds(Long[] ids);

    /**
     * 删除cms版权信息信息
     * 
     * @param id cms版权信息主键
     * @return 结果
     */
    public int deleteBizCopyrightById(Long id);
}
