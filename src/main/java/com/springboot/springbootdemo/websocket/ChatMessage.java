package com.springboot.springbootdemo.websocket;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessage {
    private Date date;
    private String userName;
    private String chatContent;
}
