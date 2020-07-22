package com.yy.yeb.service;

import com.yy.yeb.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据用户id查询菜单
     * @return
     */
    List<Menu> getMenusByAdminId();


    /**
     * 查询菜单角色
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询该角色的所有权限
     * @param id null
     * @return
     */
    List<Menu> selectAllMues(Integer id);

    /**
     * 查询该角色所具有的权限
     * @param rid 角色权限ID
     * @return 该角色所具有的权限集合
     */
    List<Integer> selectMuesByRid(Integer rid);

    /**
     * 修改角色权限
     * @param rid 该角色ID
     * @param mids 该角色要修改的权限的ID
     * @return
     */
    RespBean updateMenus(Integer rid, Integer[] mids);
}
