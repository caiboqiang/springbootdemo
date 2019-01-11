package com.springboot.springbootdemo.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加上RestController注解,使其可以响应文件上传链接
 * 如果不注册成控制层则必须加一个组件扫描，否则将无法注册
 * 所有的message都将使用json进行传输
 * 可以根据链接的直接得到roomName
 * 关于服务层的注入，只能通过Spring容器的上下文来进行赋值，否则会得到空值
 * ChatMessage为自己定义的进行信息传输的类，这里全部将其转为JSON，方便解析
 *
 */
@Slf4j
@ServerEndpoint(value = "/consult/{roomName}")
@RestController
@Log4j2//这个注解是Lombok的，可以简化代码，不用可以忽略
public class ConsultWebSocket {
    // 使用map来收集session，key为roomName，value为同一个房间的用户集合
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap<>();
    //缓存session对应的用户
    private static final Map<String, String> users = new ConcurrentHashMap<>();
    //用来缓存聊天记录的
    //private ChatCacheService chatCacheService;
    //进行文件上传具体实现细节的
    //private FileService fileService;

    /**
     * 连接创建后将上线的用户广播给组员
     */
    @OnOpen
    public void connect(@PathParam("roomName") String roomName, Session session) throws IOException {
        log.info("<<<<<<<<<<<<<创建连接：{}房间，session:{},sessionId:{}!>>>>>>>>>>>>>>",roomName,session,session.getId());
        //目前使用随机名称，可以整合自己的session管理，如shiro之类的
        String name = randomName();

        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }

        users.put(session.getId(), name);

        //向上线的人发送当前在线的人的列表
        List<ChatMessage> userList = new LinkedList<>();
        rooms.get(roomName)
                .stream()
                .map(Session::getId)
                .forEach(s -> {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setDate(new Date());
                    chatMessage.setUserName("sys");
                    chatMessage.setChatContent(users.get(s) + "在线");
                    userList.add(chatMessage);
                });
        //返回当前通道消息指自己（推送到当前页面）
        session.getBasicRemote().sendText(JSON.toJSONString(userList));

        //向房间的所有人广播谁上线了
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDate(new Date());
        chatMessage.setUserName("sys");
        chatMessage.setChatContent(users.get(session.getId()) + "上线了");
        broadcast(roomName, JSON.toJSONString(chatMessage));
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session) {
        log.info("<<<<<<<<<<<<<关闭连接：{}房间，session:{},sessionId:{}!>>>>>>>>>>>>>>",roomName,session,session.getId());
        rooms.get(roomName).remove(session);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDate(new Date());
        chatMessage.setUserName("sys");
        chatMessage.setChatContent(users.get(session.getId()) + "下线了");
        users.remove(session.getId());
        broadcast(roomName, JSON.toJSONString(chatMessage));
        log.info("<<<<<<<<<<<<<a client has disconnected!>>>>>>>>>>>>>>");
    }

    /**
     * @param msg 前台传回来的数据应为json数据
     */
    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,
                           String msg, Session session) {
        // 此处应该有html过滤，进行数据加工
        msg = users.get(session.getId()) + ":" + msg;
        System.out.println(msg);
        // 接收到信息后进行广播
        broadcast(roomName, msg);
    }

    /**
     * 发送图片，视频，语音等
     *
     * @param name     用户名
     * @param roomName 房间id
     * @param file     上传的文件
     */
    @PostMapping("/consult/{roomName}/{name}")
    public void file(@PathVariable("name") String name, @PathVariable("roomName") String roomName, MultipartFile file) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDate(new Date());
        chatMessage.setUserName(name);
        //chatMessage.setChatContent(fileService.upload(file, roomName));
        broadcast(roomName, JSON.toJSONString(chatMessage));
    }

    // 按照房间名进行广播
    private void broadcast(String roomName, String msg) {
        rooms.get(roomName).forEach(s -> {
            try {
                s.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //将聊天记录加入缓存
        //这里需要将此服务层的bean手动注入
        /*if (chatCacheService == null) {
            chatCacheService = ApplicationContextRegister.getApplicationContext().getBean(ChatCacheService.class);
        }
        chatCacheService.cacheMsg(msg, roomName, CacheType.CONSULT);*/
    }


    //随机姓名
    private String randomName() {
        Random random = new Random();
        String str = "";
        int hightPos, lowPos;
        for (int i = 0; i < 4; ++i) {
            hightPos = (176 + Math.abs(random.nextInt(39)));
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos)).byteValue();
            b[1] = (Integer.valueOf(lowPos)).byteValue();
            try {
                str += new String(b, "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
