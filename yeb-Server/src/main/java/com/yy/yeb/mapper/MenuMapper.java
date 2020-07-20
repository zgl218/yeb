package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Menu;
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
}
