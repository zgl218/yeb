package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.yeb.mapper.MenuMapper;
import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.Menu;
import com.yy.yeb.service.IMenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingtong
 * @since 2020-07-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 根据用户id查询菜单
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        return menuMapper.getMenusByAdminId(((Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    /**
     * 查询菜单角色
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }
}
