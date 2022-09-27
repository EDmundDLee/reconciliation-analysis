package com.rongxin.web.framework.config.mq;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/* *
 * @Description 设置生产者消息confirm回调函数
 * @Param
 * @Returns
 * @Author rx
 * @Date 2022/9/19 13:44
 */
@Slf4j
public class  ReturnCallBackHandler implements RabbitTemplate.ConfirmCallback {
    private static final Logger log = LoggerFactory.getLogger(ReturnCallBackHandler.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("rabbitMq Ack : {} recved",correlationData);
        }else{
            log.info("rabbitMq Ack : {} unrecved,cause {}",correlationData,cause);
        }
    }
}