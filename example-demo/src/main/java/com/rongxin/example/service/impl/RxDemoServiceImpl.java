package com.rongxin.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.example.mapper.RxDemoMapper;
import com.rongxin.example.domain.RxDemo;
import com.rongxin.example.service.IRxDemoService;

/**
 * 示例功能Service业务层处理
 * 
 * @author rx
 * @date 2022-07-28
 */
@Service
public class RxDemoServiceImpl implements IRxDemoService 
{
    @Autowired
    private RxDemoMapper rxDemoMapper;

    /**
     * 查询示例功能
     * 
     * @param id 示例功能主键
     * @return 示例功能
     */
    @Override
    public RxDemo selectRxDemoById(Long id)
    {
        return rxDemoMapper.selectRxDemoById(id);
    }

    /**
     * 查询示例功能列表
     * 
     * @param rxDemo 示例功能
     * @return 示例功能
     */
    @Override
    public List<RxDemo> selectRxDemoList(RxDemo rxDemo)
    {
        return rxDemoMapper.selectRxDemoList(rxDemo);
    }

    /**
     * 新增示例功能
     * 
     * @param rxDemo 示例功能
     * @return 结果
     */
    @Override
    public int insertRxDemo(RxDemo rxDemo)
    {
        return rxDemoMapper.insertRxDemo(rxDemo);
    }

    /**
     * 修改示例功能
     * 
     * @param rxDemo 示例功能
     * @return 结果
     */
    @Override
    public int updateRxDemo(RxDemo rxDemo)
    {
        return rxDemoMapper.updateRxDemo(rxDemo);
    }

    /**
     * 批量删除示例功能
     * 
     * @param ids 需要删除的示例功能主键
     * @return 结果
     */
    @Override
    public int deleteRxDemoByIds(Long[] ids)
    {
        return rxDemoMapper.deleteRxDemoByIds(ids);
    }

    /**
     * 删除示例功能信息
     * 
     * @param id 示例功能主键
     * @return 结果
     */
    @Override
    public int deleteRxDemoById(Long id)
    {
        return rxDemoMapper.deleteRxDemoById(id);
    }
}
