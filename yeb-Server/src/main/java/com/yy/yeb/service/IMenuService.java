package com.yy.yeb.service;

import com.yy.yeb.pojo.Menu;
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
}
