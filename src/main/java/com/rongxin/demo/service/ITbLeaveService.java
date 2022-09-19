package com.rongxin.demo.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.demo.domain.TbLeave;

/**
 * 请假示例Service接口
 * 
 * @author rx
 * @date 2022-09-15
 */
public interface ITbLeaveService extends IService<TbLeave>
{
    /**
     * 查询请假示例
     * 
     * @param id 请假示例主键
     * @return 请假示例
     */
    public TbLeave selectTbLeaveById(Long id);

    /**
     * 查询请假示例列表
     * 
     * @param tbLeave 请假示例
     * @return 请假示例集合
     */
    public List<TbLeave> selectTbLeaveList(TbLeave tbLeave);

    /**
     * 新增请假示例
     * 
     * @param tbLeave 请假示例
     * @return 结果
     */
    public int insertTbLeave(TbLeave tbLeave);

    /**
     * 修改请假示例
     * 
     * @param tbLeave 请假示例
     * @return 结果
     */
    public int updateTbLeave(TbLeave tbLeave);

    /**
     * 批量删除请假示例
     * 
     * @param ids 需要删除的请假示例主键集合
     * @return 结果
     */
    public int deleteTbLeaveByIds(Long[] ids);

    /**
     * 删除请假示例信息
     * 
     * @param id 请假示例主键
     * @return 结果
     */
    public int deleteTbLeaveById(Long id);

    public String managerApproval(Map stringToMap);

    public String personnelApproval(Map stringToMap);

    String reApply(Map stringToMap);
}
