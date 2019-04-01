package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.common.base.MessageBox;
import com.springboot.springbootdemo.rabbitMQ.RabbitMqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送
 */
@RestController
public class RabbitMqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/mqSend/{msg}")
    public MessageBox send(@PathVariable(value="msg") String msg){
        try {
            CorrelationData correlationId = new CorrelationData("666");
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,msg);
            rabbitTemplate.convertAndSend("C.B.Q", "CBQ",msg);
        } catch (AmqpException e) {
            e.printStackTrace();
            return MessageBox.build("200","sess",e.getMessage());
        }
        return MessageBox.build("200","sess");
    }
    @GetMapping(value = "/mqSend2/{msg}")
    public MessageBox send2(@PathVariable(value="msg") String msg){
        try {
            CorrelationData correlationId = new CorrelationData("666");
            rabbitTemplate.convertAndSend("C.B.Q", "CBQ2",msg);
        } catch (AmqpException e) {
            e.printStackTrace();
            return MessageBox.build("200","sess",e.getMessage());
        }
        return MessageBox.build("200","sess");
    }
}
