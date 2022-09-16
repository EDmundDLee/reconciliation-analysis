package com.rongxin.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rongxin.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.demo.service.IActReModelService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.demo.mapper.TbLeaveMapper;
import com.rongxin.demo.domain.TbLeave;
import com.rongxin.demo.service.ITbLeaveService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 请假示例Service业务层处理
 * 
 * @author rx
 * @date 2022-09-15
 */
@Service
public class TbLeaveServiceImpl extends ServiceImpl<TbLeaveMapper, TbLeave> implements ITbLeaveService
{
    @Resource
    private TaskService taskService;
    @Autowired
    private TbLeaveMapper tbLeaveMapper;
    @Autowired
    private IActReModelService actReModelService;
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 查询请假示例
     * 
     * @param id 请假示例主键
     * @return 请假示例
     */
    @Override
    public TbLeave selectTbLeaveById(Long id)
    {
        return tbLeaveMapper.selectTbLeaveById(id);
    }

    /**
     * 查询请假示例列表
     * 
     * @param tbLeave 请假示例
     * @return 请假示例
     */
    @Override
    public List<TbLeave> selectTbLeaveList(TbLeave tbLeave)
    {
        return tbLeaveMapper.selectTbLeaveList(tbLeave);
    }

    /**
     * 新增请假示例
     * 
     * @param tbLeave 请假示例
     * @return 结果
     */
    @Override
    public int insertTbLeave(TbLeave tbLeave)
    {
        tbLeave.setCreateTime(DateUtils.getNowDate());
        tbLeave.setInstId(actReModelService.applyProcess("test"));
        tbLeave.setStatus("0");
        return tbLeaveMapper.insertTbLeave(tbLeave);
    }

    /**
     * 修改请假示例
     * 
     * @param tbLeave 请假示例
     * @return 结果
     */
    @Override
    public int updateTbLeave(TbLeave tbLeave)
    {
        tbLeave.setUpdateTime(DateUtils.getNowDate());
        return tbLeaveMapper.updateTbLeave(tbLeave);
    }
    private void updateStatus(Long id,String val){
        TbLeave tbLeave = this.getById(id);
        tbLeave.setStatus(val);
        this.updateTbLeave(tbLeave);
    }
    /**
     * 批量删除请假示例
     * 
     * @param ids 需要删除的请假示例主键
     * @return 结果
     */
    @Override
    public int deleteTbLeaveByIds(Long[] ids)
    {
        return tbLeaveMapper.deleteTbLeaveByIds(ids);
    }

    /**
     * 删除请假示例信息
     * 
     * @param id 请假示例主键
     * @return 结果
     */
    @Override
    public int deleteTbLeaveById(Long id)
    {
        return tbLeaveMapper.deleteTbLeaveById(id);
    }

    /**
     * 经理审批操作
     * @param stringToMap  1为通过，0为不通过，不通过需要转回给申请人
     * @return
     */
    @Override
    public String managerApproval(Map stringToMap ){
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        int managerOpinion = Integer.valueOf(String.valueOf(stringToMap.get("isPass")));
        String comment = String.valueOf(stringToMap.get("comment"));
        String id = String.valueOf(stringToMap.get("id"));

        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null || !"dept".equals(task.getAssignee())) {
            return "找不到任务";
        }
        Map<String, Object> variablesNext = new HashMap<>();
        //通过
        if(managerOpinion == 1){
            //TODO 这里需要获取数据库定义的配置来确定人事名字
            //此处为流程图内设置的 el表达式传参（我的例子没用，代码仅供参考学习）
            variablesNext.put("personnel","leader");
        }else{
            //TODO 这里需要获取数据库定义的配置来确定回退给谁
            //此处为流程图内设置的 el表达式传参（我的例子没用，代码仅供参考学习）
            variablesNext.put("applicant","admin");

        }
        variablesNext.put("isPass", managerOpinion);

        //需要添加此句否则审批意见表中ACT_HI_COMMENT，审批人的userId是空的
        Authentication.setAuthenticatedUserId(SecurityUtils.getUsername());
        taskService.addComment(task.getId(),processInstanceId,comment);
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        updateStatus(Long.valueOf(id),"1");
        if(managerOpinion == 0){
            //该状态为驳回
            updateStatus(Long.valueOf(id),"3");
        }else{
            updateStatus(Long.valueOf(id),"1");
        }
        return "经理处理操作成功";
    }
    /**
     * 人事审批操作
     * @param  stringToMap
     * @return
     */
    @RequestMapping("personnelApproval")
    public String personnelApproval(Map stringToMap){
        String id = String.valueOf(stringToMap.get("id"));
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        int isPass = Integer.valueOf(String.valueOf(stringToMap.get("isPass")));
        String comment = String.valueOf(stringToMap.get("comment"));
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null || !"leader".equals(task.getAssignee())) {
            return "找不到任务";
        }
        Map<String, Object> variablesNext = new HashMap<>();
        //不通过需要转为给经理，通过则不需要指定人
        if(isPass == 0){
            //TODO 这里需要获取数据库定义的配置来确定回退给谁，这里的流程定义是退回给经理
            //此处为流程图内设置的 el表达式传参（我的例子没用，代码仅供参考学习）
            variablesNext.put("manager","dept");
        }
        variablesNext.put("isPass", isPass);

        //需要添加此句否则审批意见表中ACT_HI_COMMENT，审批人的userId是空的
        Authentication.setAuthenticatedUserId(SecurityUtils.getUsername());
        taskService.addComment(task.getId(),processInstanceId,comment);
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人（回退给经理或者结束流程）
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
        if(isPass == 0){
            updateStatus(Long.valueOf(id),"3");
        }else{
            updateStatus(Long.valueOf(id),"2");
        }
        return "人事操作成功";
    }
    /**
     * 申请人重新提交
     * @param stringToMap  流程实例id
     * @return
     */
    public String reApply(Map stringToMap){
        String id = String.valueOf(stringToMap.get("id"));
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        //根据流程id获取任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (task == null) {
            return "找不到任务";
        }
        Map<String, Object> variablesNext = new HashMap<>();
        //variablesNext.put("manager","经理");
        taskService.complete(task.getId(),variablesNext);//处理完成，交给下个人
        updateStatus(Long.valueOf(id),"0");
        return "申请人重新申请";
    }
}
