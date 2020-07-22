package com.yy.yeb.service;

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

    public List<Role> getRoles();
}
