package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户获取角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
