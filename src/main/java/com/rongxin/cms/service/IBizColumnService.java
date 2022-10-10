package com.rongxin.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizColumn;

/**
 * 栏目类别Service接口
 * 
 * @author rx
 * @date 2022-10-10
 */
public interface IBizColumnService extends IService<BizColumn>
{
    /**
     * 查询栏目类别
     * 
     * @param id 栏目类别主键
     * @return 栏目类别
     */
    public BizColumn selectBizColumnById(Long id);

    /**
     * 查询栏目类别列表
     * 
     * @param bizColumn 栏目类别
     * @return 栏目类别集合
     */
    public List<BizColumn> selectBizColumnList(BizColumn bizColumn);

    /**
     * 新增栏目类别
     * 
     * @param bizColumn 栏目类别
     * @return 结果
     */
    public int insertBizColumn(BizColumn bizColumn);

    /**
     * 修改栏目类别
     * 
     * @param bizColumn 栏目类别
     * @return 结果
     */
    public int updateBizColumn(BizColumn bizColumn);

    /**
     * 批量删除栏目类别
     * 
     * @param ids 需要删除的栏目类别主键集合
     * @return 结果
     */
    public int deleteBizColumnByIds(Long[] ids);

    /**
     * 删除栏目类别信息
     * 
     * @param id 栏目类别主键
     * @return 结果
     */
    public int deleteBizColumnById(Long id);
}
