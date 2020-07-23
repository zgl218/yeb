package com.yy.yeb.service;

import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询所有的角色权限
     * @return
     */
    public List<Role> getRoles();

    /**
     * 添加角色权限
     * @return
     */
    RespBean addRoles(Role role);

    /**
     * 删除用户角色权限
     * @param id
     * @return
     */
    Boolean deleteRoles(Integer id);

    /**
     * 加载角色列表
     * @return
     */
    List<Role> getRoleData();
}
