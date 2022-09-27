package com.rongxin.demo.mapper;

import java.util.List;
import com.rongxin.demo.domain.ActReModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * 工作流基础示例Mapper接口
 * 
 * @author rx
 * @date 2022-09-14
 */
public interface ActReModelMapper  extends BaseMapper<ActReModel>
{
    /**
     * 查询工作流基础示例
     * 
     * @param id 工作流基础示例主键
     * @return 工作流基础示例
     */
    public ActReModel selectActReModelById(String id);

    /**
     * 查询工作流基础示例列表
     * 
     * @param actReModel 工作流基础示例
     * @return 工作流基础示例集合
     */
    public List<ActReModel> selectActReModelList(ActReModel actReModel);

    /**
     * 新增工作流基础示例
     * 
     * @param actReModel 工作流基础示例
     * @return 结果
     */
    public int insertActReModel(ActReModel actReModel);

    /**
     * 修改工作流基础示例
     * 
     * @param actReModel 工作流基础示例
     * @return 结果
     */
    public int updateActReModel(ActReModel actReModel);

    /**
     * 删除工作流基础示例
     * 
     * @param id 工作流基础示例主键
     * @return 结果
     */
    public int deleteActReModelById(String id);

    /**
     * 批量删除工作流基础示例
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActReModelByIds(String[] ids);

    String getKeyForProcess(String keyName);

    String getKeyForProcessById(String modelId);
}
