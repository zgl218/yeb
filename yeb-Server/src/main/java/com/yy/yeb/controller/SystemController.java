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

    /**
     * 角色列表
     */
    @Resource
    private IRoleService roleService;

    @Resource
    private IAdminRoleService adminRoleService;
    @ApiOperation(value = "根据用户id查询菜单")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }



    /**
     * 初始化界面  查询
     * @return
     */
    @ApiOperation(value = "获取用户信息，进行管理")
    @GetMapping("/admin/")
    public Map<String, Admin> getAdminAndOperating (HttpServletRequest request){
        return adminService.getAdminAndOperating(request.getParameter("keywords"));
    }

    /**
     * 用户状态（开启 ，关闭）
     * @param admin
     * @return
     */
    @ApiOperation(value = "启用状态")
    @PutMapping("/admin/")
    public RespBean enabledChange(@RequestBody Map<String,Object> admin){
        Boolean flag = (Boolean) admin.get("enabled");
        if(flag==true){
            adminService.enabledChangeOpen(admin);
            return RespBean.success("已开启");
        }
        adminService.enabledChangeClose(admin);
        return RespBean.success("已禁用");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/admin/{adminId}")
    public RespBean deleteAdmin(@PathVariable Integer adminId){
        adminService.deleteAdminById(adminId);
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

}