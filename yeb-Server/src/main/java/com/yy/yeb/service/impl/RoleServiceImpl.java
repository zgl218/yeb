package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.yy.yeb.mapper.MenuMapper;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Role;
import com.yy.yeb.mapper.RoleMapper;
import com.yy.yeb.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有角色权限
     * @return
     */
    public List<Role> getRoles(){
        //创建角色权限对象
        Role role = new Role();
        //调用查询语句
        List<Role> roles = roleMapper.selectList(new QueryWrapper<>());
        //返回查询集合
        return roles;
    }

    /**
     * 添加角色权限
     * @param role 角色权限
     * @return true-成功 false-失败
     */
    @Override
    public RespBean addRoles(Role role) {
        String roleName = role.getName();
        String roleNameZh = role.getNameZh();
        //获取角色权限名字，判断是否为空
        if (null == roleName || roleName.isEmpty()
                || null == roleNameZh || roleNameZh.isEmpty()) {
            return RespBean.error("角色权限名称不能为空！");
        }
        //判断名字是否是唯一
        Role rolename = this.selectRoleName(roleName);
        if(rolename != null){
            return RespBean.error("角色权限英文名重复");
        }
        Role rolenameZh = this.selectRoleNameZh(roleNameZh);
        if(rolenameZh != null){
            return RespBean.error("角色权限中文名重复");
        }
        //创建Role对象
        Role roleObject = new Role();
        roleObject.setName("ROLE_"+roleName.trim());
        roleObject.setNameZh(roleNameZh.trim());
        int insert = roleMapper.insert(roleObject);
        //判断返回结果是否成功
        if(insert < 1){
            return RespBean.error("角色权限数据失败！");
        }
        return RespBean.success("角色权限添加成功");
    }

    /**
     * 删除用户角色权限
     * @param id
     * @return
     */
    @Override
    public Boolean deleteRoles(Integer id) {
        //判断用户角色权限是否为空
        if(null == id){
            return false;
        }
        //查询是否有用户引用该角色
        Integer count = this.selectCountAdminRole(id);
        //判断count
        if(count > 0){
            return false;
        }
        //删除用户和菜单的中间表，因为用户主键被菜单表外键所引用
        //所以要先删除外键在删除主键
        int deleteCount = menuMapper.deleteMenusRole(id);
        //删除用户角色
        int result = roleMapper.deleteRoleById(id);
        //判断影响行数
        if(result < 1){
            return false;
        }
        return true;
    }

    /**
     * 查询该用户角色下是否有存在用户使用
     * @param rid 角色ID
     * @return 多少个用户使用
     */
    private Integer selectCountAdminRole(Integer rid) {
        Integer count = roleMapper.selectCountByRid(rid);
        return count;
    }

    /**
     * 查询Role英文名字
     * @param roleName 英文名字
     * @return
     */
    private Role selectRoleName(String roleName){
        Role role = roleMapper.selectByName(roleName);
        return role;
    }

    /**
     * 查询Role中文名字
     * @param roleNameZh 中文名字
     * @return
     */
    private Role selectRoleNameZh(String roleNameZh){
        Role role = roleMapper.selectByNameZh(roleNameZh);
        return role;
    }

}
