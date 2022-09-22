package com.rongxin.web.framework.config;


import com.rongxin.web.framework.config.mq.ReturnCallBackHandler;
import com.rongxin.web.framework.config.mq.ReturnHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName MqAckConfig
 * @Description 消息消费确认配置
 * @Author rx
 * @Date 2022/9/19 13:44
 */
//dev\test没有服务暂时注释掉
//@Configuration
public class MqAckConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //初始化加载方法，对RabbitTemplate进行配置
    @PostConstruct
    void rabbitTemplate(){
        //消息发送确认，发送到交换器Exchange后触发回调
        rabbitTemplate.setConfirmCallback(new ReturnCallBackHandler());
        //消息发送确认，如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
        rabbitTemplate.setReturnCallback(new ReturnHandler());
        //自定义格式转换
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // 设置为自动提交，即使配置文件添加了配置
        factory.setConnectionFactory(connectionFactory);
        // 手动设置手动ACK
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
