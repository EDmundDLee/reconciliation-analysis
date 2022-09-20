package com.rongxin.web.framework.config.mq;

import lombok.Data;

/**
 * @ClassName MqMessage
 * @Description
 * @Author jocker
 * @Date 2020/9/3
 */
@Data
public class MqMessage {
    String appId;
    String msgBody;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
}
