package com.rongxin.web.framework.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName DirectRabbitConfig
 * @Description DirectRabbitConfig
 * @Author rx
 * @Date 2022/9/19 13:44
 */
//dev\test没有服务暂时注释掉
//@Configuration
public class DirectRabbitConfig {

    public static  final  String MY_DIRECT_QUEUE = "MyDirectQueue";
    public static  final  String MY_DIRECT_EXCHANGE = "MyDirectExchange";
    public static  final  String ROUTINGKEY = "hadoop";

//    //测试mq
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//
//    MqMessage message = new MqMessage();
//    message.setAppId("rabbitMq application");
//    message.setMsgBody("hello rabbit!");
//    //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange,指定消息唯一ID
//    rabbitTemplate.convertAndSend(DirectRabbitConfig.MY_DIRECT_EXCHANGE, DirectRabbitConfig.ROUTINGKEY, message,new CorrelationData(IdUtils.randomUUID()) );
//

    // direct queue
    @Bean
    public Queue myDirectQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(MY_DIRECT_QUEUE,true,true,false);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange myDirectExchange() {
        return new DirectExchange(MY_DIRECT_EXCHANGE,true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(myDirectQueue()).to(myDirectExchange()).with(ROUTINGKEY);
    }

}