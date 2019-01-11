package com.springboot.springbootdemo.kurento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

public class UserRegistry {
    private static final Logger log = LoggerFactory.getLogger(UserSession.class);
    private ConcurrentHashMap<String, UserSession> usersByName = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, UserSession> usersBySessionId = new ConcurrentHashMap<>();

    public void register(UserSession user) {
        usersByName.put(user.getName(), user);
        usersBySessionId.put(user.getSession().getId(), user);
        log.info("usersByName:{}::usersBySessionId:{}",usersByName.toString(),usersBySessionId.toString());
    }

    public UserSession getByName(String name) {
        return usersByName.get(name);
    }

    public UserSession getBySession(WebSocketSession session) {
        return usersBySessionId.get(session.getId());
    }

    public boolean exists(String name) {
        return usersByName.keySet().contains(name);
    }

    public UserSession removeBySession(WebSocketSession session) {
        final UserSession user = getBySession(session);
        if (user != null) {
            usersByName.remove(user.getName());
            usersBySessionId.remove(session.getId());
        }
        return user;
    }

}
