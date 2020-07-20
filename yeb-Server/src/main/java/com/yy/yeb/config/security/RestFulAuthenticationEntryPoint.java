package com.yy.yeb.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.yeb.pojo.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author YangYu
 * @Date 2020/07/18 11:23
 */
@Component
public class RestFulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("applocation/json");

        RespBean respBean = RespBean.error("尚未登录，请登录后再操作！！");
        respBean.setCode(401);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();


    }
}
