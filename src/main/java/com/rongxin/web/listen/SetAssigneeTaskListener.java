package com.rongxin.web.listen;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetAssigneeTaskListener implements TaskListener {
    private static final Logger log = LoggerFactory.getLogger(SetAssigneeTaskListener.class);
    @Override
    public void notify(DelegateTask delegateTask) {
        if("start".equals(delegateTask.getTaskDefinitionKey())){
            delegateTask.setAssignee("admin");
        }
         if("dept".equals(delegateTask.getTaskDefinitionKey())){
            delegateTask.setAssignee("dept");
        }
        if("leader".equals(delegateTask.getTaskDefinitionKey())){
            delegateTask.setAssignee("leader");
        }
    }
}
