package com.yy.yeb.chat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.vo.ChatMsg;
import com.yy.yeb.pojo.vo.WebMessage;
import com.yy.yeb.service.IAdminRoleService;
import com.yy.yeb.service.IAdminService;
import com.yy.yeb.utils.AssertUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xingtong
 */
@RestController
public class ChatController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private IAdminService iAdminService;

    @Resource
    private IAdminRoleService iAdminRoleService;

    @MessageMapping("/ws/chat")
    //传入的对象是否与所表示的主体匹配
    public void chat(Principal principal, WebMessage webMessage) throws Exception{
        Admin admin = null;
        if (principal != null){
            admin = iAdminService.getOne(new QueryWrapper<Admin>().eq("username",principal.getName()));
        }

        //用户没登陆
        AssertUtil.isTrue(null == admin,"请登录！");

        //创建返回对象
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setTo(webMessage.getTo());
        chatMsg.setContent(webMessage.getContent());
        chatMsg.setDate(LocalDateTime.now());
        chatMsg.setFrom(admin.getUsername());
        chatMsg.setFromNickName(admin.getUsername());

        //发送消息
        simpMessagingTemplate.convertAndSendToUser(webMessage.getTo(),"/queue/chat",chatMsg);

        }
        @GetMapping("/chat/admin")
        public List<Admin> getAdminList(){
            return iAdminRoleService.selectAdminList(null);
    }
}