package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户id查询菜单
     *
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 查询菜单角色
     *
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询该角色具有什么权限
     * @param rid
     * @return
     */
    List<Integer> selectMuesById(Integer rid);

    /**
     * 删除角色和菜单的中间表
     * @param rid
     * @return
     */
    int deleteMenusRole(Integer rid);

    /**
     * 插入角色和菜单的中间表数据
     * @param rid
     * @param mids
     * @return
     */
    int insertMenusRole(Integer rid, Integer[] mids);

    /**
     * 查询当前角色是否有权限
     * @param rid
     * @return
     */
    @Select("SELECT COUNT(1) FROM t_menu_role WHERE rid = #{rid}")
    Integer selectCountByRid(Integer rid);
}
