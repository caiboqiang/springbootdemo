package com.springboot.springbootdemo.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者1
 */
@Component
@Slf4j
public class FirstConsumer {
    @RabbitListener(queues = {"first-queue","second-queue"})
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);
        log.info("=========FirstConsumer  handleMessage :{}=========",message);
    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "BQ",durable = "true"),exchange = @Exchange(value = "C.B.Q",durable = "true"),key = "CBQ")})
    public void receviceReturnAuthority(Message message) {
        String s = new String(message.getBody());
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+s);
        log.info("=========FirstConsumer  handleMessage :{}=========",s);
    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "BQ2",durable = "true"),exchange = @Exchange(value = "C.B.Q",durable = "true"),key = "CBQ2")})
    public void receviceReturnAuthority2(Message message) {
        String s = new String(message.getBody());
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+s);
        log.info("=========FirstConsumer  handleMessage :{}=========",s);
    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "BQ3",durable = "true"),exchange = @Exchange(value = "C.B.Q",durable = "true"),key = "BQ3")})
    public void receviceReturnAuthority3(Message message) {
        String s = new String(message.getBody());
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+s);
        log.info("=========FirstConsumer  handleMessage :{}=========",s);
    }


}
