package com.rongxin.web.listen;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import java.util.Arrays;

public class MyExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
       // ITbLeaveService taskService = ApplicationContextHandler.getBean(ITbLeaveService.class);
       //TaskService taskService = ApplicationContextHandler.getBean(TaskService.class);
        String assigneeList = String.valueOf(delegateExecution.getVariable("assigneeList"));
        if(assigneeList != null){
            // 根据逗号分割并以数组形式重新设置进去
            delegateExecution.setVariable("assigneeList", Arrays.asList(assigneeList.split(",")));
        }
    }
}
