package com.yy.yeb.controller;


import com.yy.yeb.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@RestController
//@RequestMapping("/sys-msg")
public class SysMsgController {
    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/ws/chat")
    public ModelAndView socket(){
        ModelAndView mav = new ModelAndView("/ws/chat");
        return mav;
    }
}
