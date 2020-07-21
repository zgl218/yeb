package com.yy.yeb.controller;

import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.AdminLoginParam;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录
 *
 * @author zhoubin
 * @since 1.0.0
 */
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "登录成功返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam,HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        Admin admin = adminService.getAdminByUserName(principal.getName());
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("退出登录");
    }

}