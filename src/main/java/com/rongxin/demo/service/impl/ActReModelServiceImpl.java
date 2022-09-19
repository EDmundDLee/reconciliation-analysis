package com.rongxin.demo.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rongxin.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.uuid.IdUtils;
import com.rongxin.web.framework.config.DirectRabbitConfig;
import com.rongxin.web.framework.config.mq.MqMessage;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.demo.mapper.ActReModelMapper;
import com.rongxin.demo.domain.ActReModel;
import com.rongxin.demo.service.IActReModelService;

import javax.annotation.Resource;

/**
 * 工作流基础示例Service业务层处理
 * 
 * @author rx
 * @date 2022-09-14
 */
@Service
public class ActReModelServiceImpl extends ServiceImpl<ActReModelMapper, ActReModel> implements IActReModelService
{
    @Resource
    private RepositoryService repositoryService;
    @Autowired
    private ActReModelMapper actReModelMapper;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    ObjectMapper objectMapper;


    /**
     * 部署流程（将resource下的bpmn和png文件部署到数据库中，
     * 本项目中由于采用的事原生设计器可不考虑，如果没有通过设计器而是文件这采用此手段）
     * @return
     */
    @Override
    public Map<String, Object> deployment() {
        Deployment deployment = repositoryService.createDeployment()
                .name("部署请假流程")
                .addClasspathResource("processes/leaveProcess.bpmn20.xml")
                .addClasspathResource("processes/leaveProcess.bpmn20.png")
                .deploy();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "->>>请假流程部署成功!");
        map.put("部署流程ID:", deployment.getId());
        map.put("部署流程名称:", deployment.getName());
        map.put("部署时间:", deployment.getDeploymentTime());
        return map;
    }

    /**
     * 获取当前流程的对应任务
     * @return
     */
    @Override
    public List<Map<String,Object>> getAllProcessByKeyName(String keyName){
        String key = actReModelMapper.getKeyForProcess(keyName);

        List<Task> list = taskService.createTaskQuery().processDefinitionKey(key).list();
        List<Map<String,Object>> returnList = new ArrayList<>();
        Map<String, Object> map;
        // 输出当前用户具有的任务
        for (Task task : list) {
            map = new HashMap<>();
            map.put("流程实例id", task.getProcessInstanceId());
            map.put("任务id", task.getId());
            map.put("当前任务负责人", task.getAssignee());
            map.put("任务名称", task.getName());
            returnList.add(map);
        }
        return returnList;
    }
    @Override
    public String startProcess(String keyName) {
        //获取模型
        String key = actReModelMapper.getKeyForProcess(keyName);
        if(key !=  null){
            ProcessInstance process = processEngine.getRuntimeService().startProcessInstanceByKey(key);
            //return process.getId() + " : " + process.getProcessDefinitionId();
            return process.getId() ;
        }else{
            return "请确认是否部署";
        }
    }


    /**
     * 可以直接申请无需先进行启动操作
     * 申请人，这里是申请人初次申请，直接开始流程，如果是退回的，需要判断或者重新一个方法用于处理多次提交的
     * @param keyName
     * @return
     */
    @Override
    public String applyProcess(String keyName) {
        //设置任务委派人
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicant", SecurityUtils.getUsername());
        //流程发起人
        Authentication.setAuthenticatedUserId(SecurityUtils.getUsername());
        //variables.put("applicant", "admin");
        String key = actReModelMapper.getKeyForProcess(keyName);

        //根据流程定义key来启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key,variables);
        //业务数据与 实例id存储一下

        //申请人无需审批，直接通过，流程流转到下一个人，
        Map<String, Object> variablesNext = new HashMap<>();
        //TODO 这里需要获取数据库定义的配置来确定经理是谁
        variablesNext.put("manager","dept");
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();

       //
        //map.put("流程实例ID:", task.getProcessInstanceId());
       // map.put("任务ID:", task.getId());
       // map.put("任务负责人:", task.getAssignee());
       // map.put("任务名称:", task.getName());
        //存储数据  并通过流程实例ID 用于后续逻辑审批
        //自动通过申请人，交给下任处理
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        return pi.getProcessInstanceId();
    }

    /**
     * 根据名字来查当前人的代办任务
     * @param name
     * @return
     */
     public List<Map<String,Object>> myTask(String name) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(name).list();
        //任务列表的展示
        List<Map<String,Object>> returnList = new ArrayList<>();
        Map<String, Object> map;
        // 输出当前用户具有的任务
        for (Task task : list) {
            map = new HashMap<>();
            map.put("流程实例id", task.getProcessInstanceId());
            map.put("任务id", task.getId());
            map.put("任务负责人", task.getAssignee());
            map.put("任务名称", task.getName());
            returnList.add(map);
        }
        return returnList;
    }
    /**
     * 申请人重新提交
     * @param processInstanceId  流程实例id
     * @return
     */
    public String applicant(String processInstanceId){
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            return "找不到任务";
        }
        Map<String, Object> variablesNext = new HashMap<>();
        variablesNext.put("manager","经理");
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        return "申请人重新申请";
    }
    /**
     * 查询工作流基础示例
     * 
     * @param id 工作流基础示例主键
     * @return 工作流基础示例
     */
    @Override
    public ActReModel selectActReModelById(String id)
    {
        return actReModelMapper.selectActReModelById(id);
    }

    /**
     * 查询工作流基础示例列表
     * 
     * @param actReModel 工作流基础示例
     * @return 工作流基础示例
     */
    @Override
    public List<ActReModel> selectActReModelList(ActReModel actReModel)
    {
        return actReModelMapper.selectActReModelList(actReModel);
    }

    /**
     * 新增工作流基础示例
     * 
     * @param actReModel 工作流基础示例
     * @return 结果
     */
    @Override
    public int insertActReModel(ActReModel actReModel) throws UnsupportedEncodingException {

        RepositoryService repositoryService = processEngine.getRepositoryService();
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息
        String description = "测试";
        int revision = 1;

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, actReModel.getName());
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(actReModel.getName());
        model.setKey(actReModel.getKey());
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
        actReModel.setCreateTime(DateUtils.getNowDate());
        return revision;
    }

    /**
     * 修改工作流基础示例
     * 
     * @param actReModel 工作流基础示例
     * @return 结果
     */
    @Override
    public int updateActReModel(ActReModel actReModel)
    {
        return actReModelMapper.updateActReModel(actReModel);
    }

    /**
     * 批量删除工作流基础示例
     * 
     * @param ids 需要删除的工作流基础示例主键
     * @return 结果
     */
    @Override
    public int deleteActReModelByIds(String[] ids)
    {
        return actReModelMapper.deleteActReModelByIds(ids);
    }

    /**
     * 删除工作流基础示例信息
     * 
     * @param id 工作流基础示例主键
     * @return 结果
     */
    @Override
    public int deleteActReModelById(String id)
    {
        return actReModelMapper.deleteActReModelById(id);
    }
    /**
     * 删除已经部署的工作流
     *
     * @param modelId 流程ID
     * @return 结果
     */
    @Override
    public String deleteDeployment(String modelId)  {
        //获取模型
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Model modelData = repositoryService.getModel(modelId);
        if(modelData.getDeploymentId()!= null){
            //发布之前将旧的删除
            repositoryService.deleteDeployment(modelData.getDeploymentId());
            return "删除成功";
        }else{
            return "当前流程没有进行发布操作";
        }
    }

    /**
     * 挂起或者解挂已经部署的工作流
     * @param modelId
     * @return
     */
    @Override
    public String handUpOrDownDeployment(String modelId)  {
        //获取模型
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String key = actReModelMapper.getKeyForProcessById(modelId);
        ProcessDefinition leaveDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).singleResult();
         if(leaveDefinition != null){
             //流程状态是否是挂起
             boolean suspended = leaveDefinition.isSuspended();
             if(suspended){
                 //解挂
                 repositoryService.activateProcessDefinitionById(leaveDefinition.getId(),true,null);
                 return "解挂成功";

             }else {
                 //挂起
                 repositoryService.suspendProcessDefinitionById(leaveDefinition.getId(), true, null);
                 return "挂起成功";
             }
         }else{
             return "请部署完成后在进行挂起或解挂操作";
         }

    }

    /**
     * 发布工作流基础示例
     *
     * @param modelId 流程ID
     * @return 结果
     */
    @Override
    public String deployActReModel(String modelId) throws IOException {


        //获取模型
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Model modelData = repositoryService.getModel(modelId);
        if(modelData.getDeploymentId()!= null){
            return "已经发布，请先删除发布数据在进行发布操作";
        }
         byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return "模型数据为空，请先设计流程并成功保存，再进行发布。";
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size()==0){
            return "数据模型不符要求，请至少设计一条主线流程。";
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);



        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        return "SUCCESS";
    }

}
