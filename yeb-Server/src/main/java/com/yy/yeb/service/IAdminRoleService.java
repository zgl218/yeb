package com.yy.yeb.service;

import com.yy.yeb.pojo.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IAdminRoleService extends IService<AdminRole> {
    /**
     * 用户角色关系
     * @param adminId
     * @param roleIds
     * @return
     */
    void relationAdminRoles(Integer adminId, String[] roleIds);
}
