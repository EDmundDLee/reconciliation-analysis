package com.rongxin.demo.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rongxin.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.demo.domain.ActivitiHis;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
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
    @Autowired
    private HistoryService historyService;


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
    public String applyProcess(String keyName, Map<String, Object> variablesNext) {
        //设置任务委派人
        Map<String, Object> variables = new HashMap<>();
         //流程发起人
        Authentication.setAuthenticatedUserId(SecurityUtils.getUsername());
        //获取流程定义key
        String key = actReModelMapper.getKeyForProcess(keyName);
        //根据流程定义key来启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key,variables);//variables 此处根据实际需要后续判断是否需要传递参数
        //申请人无需审批，直接通过，流程流转到下一个人，
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        return pi.getProcessInstanceId();
    }

    /**
     * 根据名字来查当前人的代办任务
     * @param name
     * @return
     */
    @Override
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
    @Override
    public boolean reApply(String processInstanceId, Map<String, Object> variablesNext){
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            return false;
        }
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        return true;
    }

    /**
     * 单流程审批逻辑
     * @param processInstanceId  流程id
     * @param variablesNext 流程所需参数
     * @param comment 审批意见
     * @return
     */
    @Override
    public boolean singleFlow(String processInstanceId, Map<String, Object> variablesNext,String comment,String userId){
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        //根据流程代理人（即流程的审批人task.getAssignee()）进行判断是否是所设置的人
        if (task == null || !userId.equals(task.getAssignee())) {
            return false;
        }
        //需要添加此句否则审批意见表中ACT_HI_COMMENT，审批人的userId是空的
        Authentication.setAuthenticatedUserId(SecurityUtils.getUsername());
        taskService.addComment(task.getId(),processInstanceId,comment);//审批意见
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        return true;
    }

    /**
     * 会签审批(并行)
     * @param processInstanceId 流程实例id
     * @param hqname 会签人
     * @param comment 会签意见
     * @param managerOpinion 通过 0 不通过 1 通过
     * @return
     */
    @Override
    public List<Integer> joinSign(String processInstanceId,String hqname,String comment,int managerOpinion){
        if(findProcessInstanceByTaskId(processInstanceId) == null){
            return null;
        }
        //可以通过这个查询获取当前会签的一组任务
        List<Task> tasks= taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        String taskId = "";
        int spCount =0;
        int count = 0;
        int totalCount = 0;
        //对一组任务进行循环处理判断一组任务内的通过情况数
        for(Task tmp:tasks){
            String tmpTotal = taskService.getVariable(tmp.getId(), "totalCount")+"";//获取记录总数
            if(!tmpTotal.equals("null") && !tmpTotal.trim().equals("")){
                totalCount = Integer.parseInt(tmpTotal);
            }
            //当前人处理审批
            if(tmp.getAssignee().equals(hqname)){
                Authentication.setAuthenticatedUserId(hqname);
                taskService.addComment( tmp.getId(),processInstanceId,comment);//添加评论内容
                taskId = tmp.getId();//获取任务id
                String tmpCount = taskService.getVariable(tmp.getId(), "passCount")+"";//获取通过记录数，这里不能使用nrOfCompletedInstances，因为与我们业务无关
                if(!tmpCount.equals("null") && !tmpCount.trim().equals("")){
                    count = Integer.parseInt(tmpCount);
                }
                String tmpSp = taskService.getVariable(tmp.getId(), "spCount")+"";//获取审批记录数
                if(!tmpSp.equals("null") && !tmpSp.trim().equals("")){
                    spCount = Integer.parseInt(tmpSp);
                }
                spCount++;//对已评论数进行累加
                if(managerOpinion == 1   ){//选择通过则+1
                    count++;//对已通过数进行累加
                }
            }
            if( totalCount == 0){
                totalCount++;
            }
        }
        if(!taskId.equals("")){
            Map<String, Object> vars = new HashMap<String,Object>();
            //变量回写记录
            vars.put("passCount", count);
            vars.put("spCount", spCount);
            vars.put("totalCount", totalCount);
            taskService.complete(taskId,vars);
            List<Integer> list  = new ArrayList<>();
            list.add(count);
            list.add(spCount);
            list.add(totalCount);
            return list;
        }else{
            return null;
        }

    }
    /**
     * 获取当前正在执行的信息
     * @param processInstanceId 流程ID
     * @return
     */
    @Override
    public ProcessInstance findNowExcute(String processInstanceId){
        /**判断流程是否结束，查询正在执行的执行对象表*/
        ProcessInstance rpi = processEngine.getRuntimeService()//
                .createProcessInstanceQuery()//创建流程实例查询对象
                .processInstanceId(processInstanceId)
                .singleResult();
        //说明流程实例结束了
        if(rpi==null){
            /**查询历史，获取流程的相关信息*/
            HistoricProcessInstance hpi = processEngine.getHistoryService()//
                    .createHistoricProcessInstanceQuery()//
                    .processInstanceId(processInstanceId)//使用流程实例ID查询
                    .singleResult();
            System.out.println(hpi.getId()+"    "+hpi.getStartTime()+"   "+hpi.getEndTime()+"   "+hpi.getDurationInMillis());
        }
        return rpi;
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
     * 撤销审批
     * @param keyName
     * @param userName
     * @return
     */
    @Override
    public String rollBackProEx(String keyName,String userName) {

        //instId 为流程表单id  business_key
        //hisTaskId  已办任务中的任务历史id(该id为最后一个已办节点，也可以通过 instId来获取list遍历)
        Task task = taskService.createTaskQuery().processInstanceId(keyName).singleResult();
        if(task==null) {
           return "流程未启动或已执行完成，无法撤回";
        }
        //获取已完成的任务历史记录
		List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(keyName)
				.finished()
				.orderByTaskCreateTime()
				.desc()
				.list();
		//判断上一节点处理人是否为当前用户
		HistoricTaskInstance hisTask = null;
		if(htiList != null && htiList.size()>0) {
			HistoricTaskInstance hisTaskObj = htiList.get(0);

			if(userName.equals(hisTaskObj.getAssignee())) {
				hisTask = hisTaskObj;
			}
		}

        if(null==hisTask || !userName.equals(hisTask.getAssignee())) {
            return "该任务非当前用户提交，无法撤回";
        }
        HistoricTaskInstance hisTaskL = historyService.createHistoricTaskInstanceQuery().taskId(hisTask.getId()).singleResult();

        //获取上一活动的节点id
        String taskNodeId = hisTaskL.getTaskDefinitionKey();

        ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl) repositoryService.getProcessDefinition(hisTaskL.getProcessDefinitionId());

        // 取得上一步活动
        ActivityImpl currActivity = processDefinitionImpl.findActivity(taskNodeId);

        //获取节点进口
        List<PvmTransition> nextTransitionList = currActivity.getIncomingTransitions();

        // 取得当前待办活动节点
        ActivityImpl execActivity = processDefinitionImpl.findActivity(task.getTaskDefinitionKey());

        // 清除当前活动的出口
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        List<PvmTransition> pvmTransitionList = execActivity.getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();
        //把进口当做出口，重新建立通道
        List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
        TransitionImpl newTransition = execActivity.createOutgoingTransition();
        newTransition.setDestination(currActivity);
        newTransitions.add(newTransition);
        // 完成任务
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(keyName).list();
        for (Task taskObj : tasks) {
            Map<String,Object> currentVariables = new HashMap<String,Object>();
            //此处可以根据也许需求获取上一节点处理人，本人使用的是自己自定义的动态表单
            //List<String> userList = new ArrayList<>();
            //userList.add("0122");
            //currentVariables.put("transactorIdList",userList);
            Authentication.setAuthenticatedUserId(userName);
            taskService.addComment(taskObj.getId(), taskObj.getProcessInstanceId(), "撤回");

            taskService.complete(taskObj.getId(), currentVariables);
            //删除历史、此处执行这两行代码在ACT_HI_TASKINST表中是看不到撤回记录的，但是在ACT_HI_ACTINST表中能看到全部记录
            //historyService.deleteHistoricTaskInstance(taskObj.getId());
            //historyService.deleteHistoricTaskInstance(hisTask.getId());
        }
        // 恢复方向
        for (TransitionImpl transitionImpl : newTransitions) {
            execActivity.getOutgoingTransitions().remove(transitionImpl);
        }
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
        //后面需要处理的就是 已办的流程历史回显 和 已办的流程表单回显，可根据自己业务需求来调整
        return "撤回成功";
    }
    @Override
    public boolean rollBackData(String keyName) {
        // 流程实例是否在执行
        Execution execution = runtimeService.createExecutionQuery().parentId(keyName).singleResult();
        if(Objects.isNull(execution)){
            //学生取消，结束流程
            runtimeService.deleteProcessInstance(keyName, "申请人"+SecurityUtils.getUsername()+"取消申请");
        }
        return true;
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
    /**
     * 根据流程实例ID和任务key值查询所有同级任务集合
     *
     * @param processInstanceId
     * @param key
     * @return
     */
    private List<Task> findTaskListByKey(String processInstanceId, String key) {
        return taskService.createTaskQuery().processInstanceId(
                processInstanceId).taskDefinitionKey(key).list();
    }
    /**
     * 根据实例ID获取对应的流程实例
     *
     * @param keyName
     * @return
     * @throws Exception
     */
    public ProcessInstance findProcessInstanceByTaskId(String keyName){
        // 找到流程实例
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery().processInstanceId(keyName)
                .singleResult();
        return processInstance;
    }
    @Override
    public  List<ActivitiHis> getHistory(String instanceId){
//        List<HistoricActivityInstance>  list = processEngine.getHistoryService() // 历史相关Service
//                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
//                .processInstanceId(instanceId) // 执行流程实例id
//                .finished()
//                .list();
//        for(HistoricActivityInstance hai:list){
//            System.out.println("活动ID:"+hai.getId());
//            System.out.println("流程实例ID:"+hai.getProcessInstanceId());
//            System.out.println("活动名称："+hai.getActivityName());
//            System.out.println("办理人："+hai.getAssignee());
//            System.out.println("开始时间："+hai.getStartTime());
//            System.out.println("结束时间："+hai.getEndTime());
//            System.out.println("=================================");
//        }
        List<ActivitiHis> listData = new ArrayList<>();
        ActivitiHis activitiHis = null;
         List<HistoricTaskInstance> list1 = processEngine.getHistoryService() // 历史相关Service
                .createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .processInstanceId(instanceId) // 用流程实例id查询
                .finished() // 查询已经完成的任务
                .list();
        for(HistoricTaskInstance hti:list1){
            activitiHis = new ActivitiHis();
            activitiHis.setTaskId(hti.getId());
            activitiHis.setInstanceId(hti.getProcessInstanceId());
            activitiHis.setTaskName(hti.getName());
            activitiHis.setStartTime(String.valueOf(hti.getStartTime()));
            activitiHis.setAssignee(hti.getAssignee());
            activitiHis.setEndTime(String.valueOf(hti.getEndTime()));
            List<Comment> commonts = taskService.getTaskComments(hti.getId());//这个写法效率低后续考虑正常写sql
            activitiHis.setCommentList(commonts);
            listData.add(activitiHis);
        }
        // 查询事件与评论
        //List<Comment> commonts1 = taskService.getProcessInstanceComments(instanceId);
        // System.out.println("流程评论（事件）数量：" + commonts1.size());
        return listData;
    }
}
