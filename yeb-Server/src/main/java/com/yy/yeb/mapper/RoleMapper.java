package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id获取角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 查询用户角色中是否存在该英文名字
     * @param roleName 英文名字
     * @return 用户信息
     */
    Role selectByName(String roleName);

    /**
     * 查询用户角色中是否存在该中文名字
     * @param roleNameZh 中文名字
     * @return 用户信息
     */
    Role selectByNameZh(String roleNameZh);

    /**
     * 查询该用户角色下是否有存在用户使用
     * @param rid 角色ID
     * @return 多少个用户使用
     */
    @Select("SELECT COUNT(1) FROM t_admin_role WHERE rid = #{rid}")
    Integer selectCountByRid(Integer rid);

    /**
     * 删除该用户角色
     * @param id 用户角色ID
     * @return 影响行数
     */
    @Delete("DELETE FROM t_role WHERE id = #{id}")
    int deleteRoleById(Integer id);

    List<Role> getRoleData();
}
