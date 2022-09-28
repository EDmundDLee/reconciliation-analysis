package com.rongxin.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rongxin.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.demo.domain.ActivitiHis;
import com.rongxin.demo.service.IActReModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.demo.mapper.TbLeaveMapper;
import com.rongxin.demo.domain.TbLeave;
import com.rongxin.demo.service.ITbLeaveService;

/**
 * 请假示例Service业务层处理
 * 
 * @author rx
 * @date 2022-09-15
 */
@Service
public class TbLeaveServiceImpl extends ServiceImpl<TbLeaveMapper, TbLeave> implements ITbLeaveService
{
    @Autowired
    private TbLeaveMapper tbLeaveMapper;
    @Autowired
    private IActReModelService actReModelService;

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
    public int insertTbLeave(TbLeave tbLeave){

        //TODO 设置下一流程所需要的参数集合
        //设置下一流程所需要的参数集合
        Map<String, Object> variablesNext = new HashMap<>();
        List<String> assigneeList = new ArrayList<String>();
        assigneeList.add("张三");
        assigneeList.add("李四");
        assigneeList.add("王五");
        variablesNext.put("passCount",0);
        variablesNext.put("totalCount",3);
        variablesNext.put("spCount",0);
        variablesNext.put("assigneeList",assigneeList);
        tbLeave.setCreateTime(DateUtils.getNowDate());
        //创建工作流第一步  根据process标识 获取流程
        tbLeave.setInstId(actReModelService.applyProcess("test2",variablesNext));
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
        //获取前台传递参数
        String processInstanceId = String.valueOf(stringToMap.get("instId"));//实例ID(当前流程id)
        int managerOpinion = Integer.valueOf(String.valueOf(stringToMap.get("isPass")));//是否通过
        String comment = String.valueOf(stringToMap.get("comment"));//审批内容
        String id = String.valueOf(stringToMap.get("id"));//业务数据ID
        //设置流程所需要的参数
        Map<String, Object> variablesNext = new HashMap<>();
        variablesNext.put("isPass", managerOpinion);
        //单流程审批
        if(actReModelService.singleFlow(processInstanceId,variablesNext,comment,"dept")){
            if(managerOpinion == 0){
                //该状态为驳回
                updateStatus(Long.valueOf(id),"4");
            }else{
                updateStatus(Long.valueOf(id),"2");
            }
        }else{
            return "找不到任务";
        }
        //处理业务逻辑状态
        return "经理处理操作成功";
    }
    /**
     * 人事审批操作
     * @param  stringToMap
     * @return
     */
    @Override
    public String personnelApproval(Map stringToMap){
        //获取前台传递参数
        String processInstanceId = String.valueOf(stringToMap.get("instId"));//实例ID(当前流程id)
        int isPass = Integer.valueOf(String.valueOf(stringToMap.get("isPass")));//是否通过
        String comment = String.valueOf(stringToMap.get("comment"));//审批内容
        String id = String.valueOf(stringToMap.get("id"));//业务数据ID
        //设置流程所需要的参数
        Map<String, Object> variablesNext = new HashMap<>();
        variablesNext.put("isPass", isPass);
        //单流程审批
        if(actReModelService.singleFlow(processInstanceId,variablesNext,comment,"leader")){
            if(isPass == 0){
                updateStatus(Long.valueOf(id),"4");
            }else{
                updateStatus(Long.valueOf(id),"3");
            }
        }else{
            return "找不到任务";
        }

        return "人事操作成功";
    }
    /**
     * 申请人重新提交
     * @param stringToMap  流程实例id
     * @return
     */
    @Override
    public String reApply(Map stringToMap){
        String id = String.valueOf(stringToMap.get("id"));
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        //配置参数
        Map<String, Object> variablesNext = new HashMap<>();
        List<String> assigneeList = new ArrayList<String>();
        assigneeList.add("张三");
        assigneeList.add("李四");
        assigneeList.add("王五");
        variablesNext.put("passCount",0);
        variablesNext.put("totalCount",3);
        variablesNext.put("spCount",0);
        variablesNext.put("assigneeList",assigneeList);
        //重新提交方法调用
        if(actReModelService.reApply(processInstanceId, variablesNext)){
            updateStatus(Long.valueOf(id),"0");
            return "重新申请成功";
        }else{
            return "找不到任务";
        }
    }

    /**
     * 撤回申请
     * @param stringToMap
     * @return
     */
    @Override
    public String rollBackData(Map stringToMap) {
        String id = String.valueOf(stringToMap.get("id"));
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        if(actReModelService.rollBackData(processInstanceId)){
            deleteTbLeaveById( Long.valueOf(id));
        }
        return "取消成功";
    }

    @Override
    public String handleHistory(String instanceId) {
        List<ActivitiHis> list = actReModelService.getHistory(instanceId);
        return "成功";
    }

    @Override
    public String handleReturn(Map stringToMap) {
        String id = String.valueOf(stringToMap.get("id"));
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        //重新提交方法调用
        String result = actReModelService.rollBackProEx(processInstanceId,"dept");
        if("撤回成功".equals(result)){
            updateStatus(Long.valueOf(id),"1");
            return result;
        }else{
            return result;
        }
    }

    /**
     * 会签审批逻辑示例
     * @param stringToMap
     * @return
     */
    @Override
    public String handleMoreP(Map stringToMap){
        String processInstanceId = String.valueOf(stringToMap.get("instId"));
        int managerOpinion = Integer.valueOf(String.valueOf(stringToMap.get("isPass")));
        String comment = String.valueOf(stringToMap.get("comment"));
        String id = String.valueOf(stringToMap.get("id"));
        String hqname = String.valueOf(stringToMap.get("huiqian"));
        String result = "";
        List<Integer> list = actReModelService.joinSign(processInstanceId,hqname,comment,managerOpinion);
        if(null == list){
            return "当前人是否有审批任务或者是否已经审批完成";
        }else{
            int spCount = list.get(0);
            int count = list.get(1);
            int totalCount = list.get(2);
            if(spCount == totalCount){
                if(count/totalCount<1 ){
                    updateStatus(Long.valueOf(id),"4");
                }else{
                    updateStatus(Long.valueOf(id),"1");
                }
            }
            if(count/totalCount >=1){
                result = hqname + "审批通过，等待后续经理审批" ;
            }else{
                result = hqname + "已审批，等待后续会签审批" ;
            }
        }
        return result;
    }

}
