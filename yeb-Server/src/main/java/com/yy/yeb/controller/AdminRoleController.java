package com.yy.yeb.controller;


import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IAdminRoleService;
import com.yy.yeb.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system")
public class AdminRoleController {

    @Resource
    private IAdminRoleService adminRoleService;
    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "角色记录管理")
    @RequestMapping("/admin/role")
    public RespBean relationAdminRoles(HttpServletRequest request){
        //获取用户id
        Integer adminId = Integer.valueOf(request.getParameter("adminId"));
        System.out.println(adminId);
        //获取角色id
        String[] roleIds = request.getParameterValues("rids");
        for(String r:roleIds){
            System.out.println(r);
        }
        adminRoleService.relationAdminRoles(adminId,roleIds);
        return RespBean.success("操作成功！");
    }

}
