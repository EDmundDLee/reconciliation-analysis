package com.rongxin.web.listen;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import java.util.Arrays;
import java.util.List;

/**
 * 20220928
 * 工作流----执行监听器
 */
public class MyExecutionListener implements ExecutionListener  {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
       // ITbLeaveService taskService = ApplicationContextHandler.getBean(ITbLeaveService.class);
       //TaskService taskService = ApplicationContextHandler.getBean(TaskService.class);
        //此处监听在示例代码没有启用 是通过创建的时候加入的list数据，可根据实际情况进行参考 如果后续启用了请更改注释 20220928
        String assigneeList = String.valueOf(delegateExecution.getVariable("assigneeList"));
        List<String> list = (List<String>) delegateExecution.getVariable("assigneeList");
        StringBuilder sb = new StringBuilder();
        for (String s : list)
        {
            sb.append(s);
            sb.append(",");
        }
        if(assigneeList != null){
            // 根据逗号分割并以数组形式重新设置进去
            delegateExecution.setVariable("assigneeList", Arrays.asList(sb.toString().split(",")));
        }
    }

}
