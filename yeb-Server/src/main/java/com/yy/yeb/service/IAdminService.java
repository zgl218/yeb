package com.yy.yeb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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


    RespBean updateAdmin(AdminUserDetail adminUserDetail);

    RespBean updatePass(PassWordDetail passWordDetail);
}
