package com.yy.yeb.controller;


import com.yy.yeb.pojo.Menu;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Role;
import com.yy.yeb.service.IMenuService;
import com.yy.yeb.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    /**
     * 展示所有角色权限
     * @return
     */
    @ApiOperation(value = "显示角色列表")
    @GetMapping
    public List<Role> getRoles(){
        //调用service中的方法
        List<Role> roles = roleService.getRoles();
        return roles;
    }

    /**
     * 添加角色权限
     * @param role 角色权限
     * @return
     */
    @ApiOperation(value = "添加角色权限")
    @PostMapping("/role")
    public RespBean addRoles(@RequestBody Role role){
        RespBean respBean = roleService.addRoles(role);
        return respBean;
    }

    /**
     * 展示角色下所有的权限
     * @param id
     * @return
     */
    @ApiOperation(value = "展示所有角色权限")
    @GetMapping("/menus")
    @ResponseBody
    public List<Menu> getRoleMues(Integer id){
        List<Menu> menus = menuService.selectAllMues(id);
        return menus;
    }

    /**
     * 查询该角色所局域的权限进行回显
     * @param rid 改角色Id
     * @return 该角色具有的角色权限的Mmid
     */
    @ApiOperation(value = "展示角色权限")
    @GetMapping("/mid/{rid}")
    @ResponseBody
    public List<Integer> getMids(@PathVariable Integer rid){
       List<Integer> mues =menuService.selectMuesByRid(rid);
       return mues;
    }

    /**
     * 修改用户权限
     * @param rid
     * @param mids
     * @return
     */
    @ApiOperation(value = "修改角色权限")
    @PutMapping("/")
    public RespBean updateMues(Integer rid,Integer[] mids){
        RespBean respBean = menuService.updateMenus(rid, mids);
        return respBean;
    }

    @ApiOperation(value = "添加角色权限")
    @DeleteMapping("/role/{id}")
    public RespBean deleteRoles(@PathVariable("id") Integer id){
        Boolean falg = roleService.deleteRoles(id);
        if(falg == false){
            return RespBean.error("该角色有被用户使用，请在操作员管理中清除用户角色");
        }
        return RespBean.success("删除成功");
    }

}
