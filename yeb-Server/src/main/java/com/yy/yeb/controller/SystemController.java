package com.yy.yeb.controller;

import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.Menu;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Role;
import com.yy.yeb.service.IAdminRoleService;
import com.yy.yeb.service.IAdminService;
import com.yy.yeb.service.IMenuService;
import com.yy.yeb.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 系统配置Controller
 *
 * @author zhoubin
 * @since 1.0.0
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private IMenuService menuService;

    @Resource
    private IAdminService adminService;

    @Resource
    private IAdminRoleService adminRoleService;
    @ApiOperation(value = "根据用户id查询菜单")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }



}