package com.yy.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingtong
 * @since 2020-07-17
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录成功返回token
     * @param username
     * @param password
     * @return
     */
    RespBean login(String username, String password, String code,HttpServletRequest request);


    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户获取角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
