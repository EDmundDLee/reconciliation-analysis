package com.rongxin.demo.service;

import com.rongxin.demo.domain.RxDemo;

import java.util.List;

/**
 * 示例功能Service接口
 * 
 * @author rx
 * @date 2022-07-29
 */
public interface IRxDemoService 
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
     * 批量删除示例功能
     * 
     * @param ids 需要删除的示例功能主键集合
     * @return 结果
     */
    public int deleteRxDemoByIds(Long[] ids);

    /**
     * 删除示例功能信息
     * 
     * @param id 示例功能主键
     * @return 结果
     */
    public int deleteRxDemoById(Long id);
}
