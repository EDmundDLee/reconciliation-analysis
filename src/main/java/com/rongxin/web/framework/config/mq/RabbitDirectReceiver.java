package com.rongxin.web.framework.config.mq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rongxin.web.framework.config.DirectRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;
//dev\test没有服务暂时注释掉
//@Component
@Slf4j
public class RabbitDirectReceiver {
    private static final Logger log = LoggerFactory.getLogger(RabbitDirectReceiver.class);

    /* *
     * 1. direct 下多个消费者只有一个消费者能消费消息，类似于负载，默认是轮询。
     * 2. 重复消费，中心思想，在redis或者db中存储消息唯一值，消费前判断下，多线程环境下需要考虑线程安全。
     *
     */

    @RabbitListener(queues = DirectRabbitConfig.MY_DIRECT_QUEUE, containerFactory="rabbitListenerContainerFactory")
    public void process(Message message, Channel channel) throws IOException {
        try {
            // 业务代码
            // .......
            // multiple: false 只确认当前消费者已消费消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            String result = new String(message.getBody(),"utf-8");
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            // multiple: true  确认改消息已被所有消费者消费
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e){
            log.error("消息处理异常！",e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
        }
    }
}