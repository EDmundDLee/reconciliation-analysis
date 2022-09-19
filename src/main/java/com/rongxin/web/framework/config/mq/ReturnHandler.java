package com.rongxin.web.framework.config.mq;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/* *
 * @Description 设置生产者消息returns回调函数
 * @Param 通过实现 ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调
 * @Returns
 * @Author jocker
 * @Date 2022/9/19 13:44
 */
@Slf4j
public class  ReturnHandler implements RabbitTemplate.ReturnCallback {
    private static final Logger log = LoggerFactory.getLogger(ReturnHandler.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("rabbitmq : {} returned in {} with {},{}",message,exchange,replyCode,replyText);
    }
}