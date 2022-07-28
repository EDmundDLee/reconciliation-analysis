package com.rongxin.example.mapper;

import java.util.List;
import com.rongxin.example.domain.RxDemo;

/**
 * 示例功能Mapper接口
 * 
 * @author rx
 * @date 2022-07-28
 */
public interface RxDemoMapper 
{
    /**
     * 查询示例功能
     * 
     * @param id 示例功能主键
     * @return 示例功能
     */
    public RxDemo selectRxDemoById(Long id);

    /**
     * 查询示例功能列表
     * 
     * @param rxDemo 示例功能
     * @return 示例功能集合
     */
    public List<RxDemo> selectRxDemoList(RxDemo rxDemo);

    /**
     * 新增示例功能
     * 
     * @param rxDemo 示例功能
     * @return 结果
     */
    public int insertRxDemo(RxDemo rxDemo);

    /**
     * 修改示例功能
     * 
     * @param rxDemo 示例功能
     * @return 结果
     */
    public int updateRxDemo(RxDemo rxDemo);

    /**
     * 删除示例功能
     * 
     * @param id 示例功能主键
     * @return 结果
     */
    public int deleteRxDemoById(Long id);

    /**
     * 批量删除示例功能
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRxDemoByIds(Long[] ids);
}
