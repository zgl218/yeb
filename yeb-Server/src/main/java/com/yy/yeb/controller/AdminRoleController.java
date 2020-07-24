package com.yy.yeb.controller;


import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Role;
import com.yy.yeb.service.IAdminRoleService;
import com.yy.yeb.service.IAdminService;
import com.yy.yeb.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    /**
     * 角色列表
     */
    @Resource
    private IRoleService roleService;

    /**
     * 初始化界面  查询
     * @return
     */
    @ApiOperation(value = "获取用户信息，进行管理")
    @GetMapping("/admin/")
    public Map<String, Admin> getAdminAndOperating (HttpServletRequest request){
        return adminRoleService.getAdminAndOperating(request.getParameter("keywords"));
    }

    /**
     * 用户状态（开启 ，关闭）
     * @param admin
     * @return
     */
    @ApiOperation(value = "操作员状态")
    @PutMapping("/admin/")
    public RespBean enabledChange(@RequestBody Map<String,Object> admin){
        Boolean flag = (Boolean) admin.get("enabled");
        if(flag==true){
            adminRoleService.enabledChangeOpen(admin);
            return RespBean.success("已开启");
        }
        adminRoleService.enabledChangeClose(admin);
        return RespBean.success("已禁用");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/admin/{adminId}")
    public RespBean deleteAdmin(@PathVariable Integer adminId){
        adminRoleService.deleteAdminById(adminId);
        return RespBean.success("删除成功");
    }

    /**
     * 加载角色列表
     * @return
     */
    @ApiOperation(value = "加载角色")
    @GetMapping("/admin/roles")
    public List<Role> getRoleData(){
        return roleService.getRoleData();
    }

    @ApiOperation(value = "操作员角色管理")
    @RequestMapping("/admin/role")
    public RespBean relationAdminRoles(HttpServletRequest request){
        //获取用户id
        Integer adminId = Integer.valueOf(request.getParameter("adminId"));
        System.out.println(adminId);
        //获取角色id
        String[] roleIds = request.getParameterValues("rids");
        adminRoleService.relationAdminRoles(adminId,roleIds);
        return RespBean.success("用户角色更新操作成功！");
    }
}
