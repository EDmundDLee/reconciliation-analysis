package com.rongxin.demo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.demo.domain.ActReModel;

/**
 * 工作流基础示例Service接口
 * 
 * @author rx
 * @date 2022-09-14
 */
public interface IActReModelService extends IService<ActReModel>
{
    public Map<String, Object> deployment();

    public List<Map<String,Object>> getAllProcessByKeyName(String keyName);

    public String startProcess(String keyName);

    public String applyProcess(String keyName);

    public String handUpOrDownDeployment(String modelId);

    public String deleteDeployment(String modelId);


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
    public int insertActReModel(ActReModel actReModel) throws UnsupportedEncodingException;

    /**
     * 修改工作流基础示例
     * 
     * @param actReModel 工作流基础示例
     * @return 结果
     */
    public int updateActReModel(ActReModel actReModel);

    /**
     * 批量删除工作流基础示例
     * 
     * @param ids 需要删除的工作流基础示例主键集合
     * @return 结果
     */
    public int deleteActReModelByIds(String[] ids);

    /**
     * 删除工作流基础示例信息
     * 
     * @param id 工作流基础示例主键
     * @return 结果
     */
    public int deleteActReModelById(String id);
    /**
     * 新增工作流基础示例
     *
     * @param modelId 流程id
     * @return 结果
     */
    public String deployActReModel(String modelId) throws IOException;



}
