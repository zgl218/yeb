package com.yy.yeb.service;

import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.pojo.Role;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户获取角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 初始化界面
     * @param keywords
     * @return
     */
    Map<String, Admin> getAdminAndOperating(String keywords);


    /**
     * 关闭用户
     * @param admin
     * @return
     */
    Integer enabledChangeClose(Map<String,Object> admin);

    /**
     * 开启用户
     * @param admin
     * @return
     */
    Integer enabledChangeOpen(Map<String, Object> admin);

    /**
     * 根据用户id删除用户
     * @param adminId
     * @return
     */
    Integer deleteAdminById(Integer adminId);

    List<Admin> selectAdminList(String keywords);
}
