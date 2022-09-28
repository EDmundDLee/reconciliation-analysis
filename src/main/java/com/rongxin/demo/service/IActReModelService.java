package com.rongxin.demo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.demo.domain.ActReModel;
import com.rongxin.demo.domain.ActivitiHis;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 工作流基础示例Service接口
 * 
 * @author rx
 * @date 2022-09-14
 */
public interface IActReModelService extends IService<ActReModel>
{

    /**
     * 重新提交
     * @return
     */
    public boolean reApply(String processInstanceId, Map<String, Object> variablesNext);
    //部署
    public Map<String, Object> deployment();

    /**
     * 单流程审批  流程ID  传递参数  审批意见
     * @param processInstanceId
     * @param variablesNext
     * @param comment
     * @return
     */
    public boolean singleFlow(String processInstanceId, Map<String, Object> variablesNext,String comment,String userId);
    /**
     * 会签审批(并行)
     * @param processInstanceId 流程实例id
     * @param hqname 会签人
     * @param comment 会签意见
     * @param managerOpinion 通过 0 不通过 1 通过
     * @return
     */
    public List<Integer> joinSign(String processInstanceId,String hqname,String comment,int managerOpinion);
    /**
     * 根据名字来查当前人的代办任务
     * @param name
     * @return
     */
    public List<Map<String,Object>> myTask(String name);
    /**
     * 获取当前正在执行的信息
     * @param processInstanceId 流程ID
     * @return
     */
    public ProcessInstance findNowExcute(String processInstanceId);

    public List<Map<String,Object>> getAllProcessByKeyName(String keyName);

    public String startProcess(String keyName);

    /**
     * 流程发起
     * @param keyName 流程key
     * @param variablesNext 下一流程所需要的参数
     * @return
     */
    public String applyProcess(String keyName, Map<String, Object> variablesNext);

    public String handUpOrDownDeployment(String modelId);

    public String deleteDeployment(String modelId);

    public boolean rollBackData(String keyName);

    public String rollBackProEx(String keyName,String userName);

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


    public List<ActivitiHis> getHistory(String instanceId);
}
