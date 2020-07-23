package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.AdminRole;
import com.yy.yeb.mapper.AdminRoleMapper;
import com.yy.yeb.service.IAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Resource
    private AdminRoleMapper adminRoleMapper;
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
        for (String rid:roleIds){
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRid(Integer.parseInt(rid));
            adminRoles.add(adminRole);
        }
        adminRoleMapper.insertBatch(adminRoles);
    }
}
