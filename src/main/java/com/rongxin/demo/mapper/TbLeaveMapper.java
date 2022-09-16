package com.rongxin.demo.mapper;

import java.util.List;
import com.rongxin.demo.domain.TbLeave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 请假示例Mapper接口
 * 
 * @author rx
 * @date 2022-09-15
 */
public interface TbLeaveMapper  extends BaseMapper<TbLeave>
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
     * 删除请假示例
     * 
     * @param id 请假示例主键
     * @return 结果
     */
    public int deleteTbLeaveById(Long id);

    /**
     * 批量删除请假示例
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbLeaveByIds(Long[] ids);
}
