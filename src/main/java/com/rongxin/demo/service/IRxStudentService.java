package com.rongxin.demo.service;

import java.io.IOException;
import java.util.List;
import com.rongxin.demo.domain.RxStudent;

/**
 * 学生Service接口
 * 
 * @author rx
 * @date 2022-09-06
 */
public interface IRxStudentService
{
    /**
     * 查询学生
     * 
     * @param id 学生主键
     * @return 学生
     */
    public RxStudent selectRxStudentById(String id) throws IOException;

    /**
     * 查询学生列表
     * 
     * @param rxStudent 学生
     * @return 学生集合
     */
    public List<RxStudent> selectRxStudentList(RxStudent rxStudent) throws IOException;

    /**
     * 新增学生
     * 
     * @param rxStudent 学生
     * @return 结果
     */
    public String insertRxStudent(RxStudent rxStudent) throws IOException;

    /**
     * 修改学生
     * 
     * @param rxStudent 学生
     * @return 结果
     */
    public String updateRxStudent(RxStudent rxStudent) throws IOException;

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的学生主键集合
     * @return 结果
     */
    public int deleteRxStudentByIds(String[] ids) throws IOException;

    /**
     * 删除学生信息
     * 
     * @param id 学生主键
     * @return 结果
     */
    public String deleteRxStudentById(String id) throws IOException;
}
