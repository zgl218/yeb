package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.yeb.mapper.AdminMapper;
import com.yy.yeb.mapper.RoleMapper;
import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.AdminRole;
import com.yy.yeb.mapper.AdminRoleMapper;
import com.yy.yeb.pojo.Role;
import com.yy.yeb.service.IAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Resource
    private AdminRoleMapper adminRoleMapper;
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private RoleMapper roleMapper;
    /**
     * 用户角色关系
     * @param adminId
     * @param roleIds
     */
    @Override
    public void relationAdminRoles(Integer adminId, String[] roleIds) {
        //删除所有的用户角色
        adminRoleMapper.deleteAdminRoleByAdminId(adminId);
        /**
         * 重新添加角色
         */
        List<AdminRole> adminRoles = new ArrayList<>();
//        非空判断如果传来的是空直接反回
        if (roleIds == null) {
            return;
        }
//        不为空，进行过添加角色操作
        else {
            for (String rid : roleIds) {
                AdminRole adminRole = new AdminRole();
                adminRole.setAdminId(adminId);
                adminRole.setRid(Integer.parseInt(rid));
                adminRoles.add(adminRole);
            }
            adminRoleMapper.insertBatch(adminRoles);
        }
    }


    /**
     * 根据用户获取角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 初始化界面  查询操作  如果有参数传入查询相应的管理员信息，如果没有参数传入查询所有操作员信息
     * @param keywords
     * @return
     */
    @Override
    public Map<String, Admin> getAdminAndOperating(String keywords) {
//        创建一个map <key="操作员姓名"  value="操作员信息">
        Map<String,Admin> adminMap = new HashMap<>();
        //获取查询的用户
        List<Admin> listAdmin = adminMapper.operatingTheAdmin(keywords);
//        listAdmin.forEach(System.out::println);
        for (Admin admins: listAdmin){
            admins.setPassword(null);
            admins.setRoles(getRoles(admins.getId()));
            adminMap.put(admins.getName(),admins);
        }
        return adminMap;
    }

    /**
     * 启用  ----->  关闭
     * @param admin
     * @return
     */
    @Override
    public Integer enabledChangeClose(Map<String,Object> admin) {
        return adminMapper.enabledChangeCloseById((Integer) admin.get("id"));
    }

    /**
     * 关闭  ----->  启用
     * @param admin
     * @return
     */
    @Override
    public Integer enabledChangeOpen(Map<String, Object> admin) {
        return adminMapper.enabledChangeOpenById((Integer) admin.get("id"));
    }

    /**
     * 删除用户
     * @param adminId
     * @return
     */
    @Override
    public Integer deleteAdminById(Integer adminId) {
        return adminMapper.deleteAdminById(adminId);
    }

    @Override
    public List<Admin> selectAdminList(String keywords) {
        //‘’为没有查询条件
        String empty = "''";
        //设置查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

        //传递keywords，条件搜索
        if (StringUtils.isNotBlank(keywords) && !empty.equals(keywords)){
            queryWrapper.like("name",keywords);
        }
        //查询操作员列表
        List<Admin> adminList = adminMapper.selectList(queryWrapper);
        //查询角色
        adminList.forEach(admin -> {
            //设置角色信息
            admin.setRoles(getRoles(admin.getId()));
            //将密码设为null
            admin.setPassword(null);
        });
        return adminList;
    }
}
