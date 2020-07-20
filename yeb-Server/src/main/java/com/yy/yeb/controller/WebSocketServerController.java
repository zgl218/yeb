package com.yy.yeb.controller;

import com.alibaba.fastjson.JSONObject;
import com.yy.yeb.pojo.MsgVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websockt处理类Controller
 *
 * @author xingtong
 */
@Slf4j
@Component
@ServerEndpoint("/")
public class WebSocketServerController {

    /**
     * 房间号 -> 组成员信息
     */
    private static ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();

    /**
     * 房间号 -> 在线人数
     */
    private static ConcurrentHashMap<String, Set<Integer>> onlineUserMap = new ConcurrentHashMap<>();

    @OnMessage
    public void onMessage(@PathParam("sid") String sid,@PathParam("userId") Integer userId,String message){
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        Set<Integer> onlineUserList = onlineUserMap.get(sid);
        //先一个群组内的成员发送消息
        sessionList.forEach(item -> {
            try {
                //json字符串转对象
                MsgVO msg = JSONObject.parseObject(message,MsgVO.class);
                msg.setCount(onlineUserList.size());
                //json对象转字符串
                String text = JSONObject.toJSONString(msg);
                item.getBasicRemote().sendText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid,@PathParam("userId") Integer userId){
        List<Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid,k -> new ArrayList<>());
        Set<Integer> onlineUserList = onlineUserMap.computeIfAbsent(sid,k -> new HashSet<>());
        onlineUserList.add(userId);
        sessionList.add(session);

        //发送上线通知




    }

}
