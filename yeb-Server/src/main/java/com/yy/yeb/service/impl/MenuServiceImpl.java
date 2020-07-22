package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.yeb.mapper.MenuMapper;
import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.Menu;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IMenuService;
import io.swagger.models.auth.In;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 查询角色所有权限
     * @param id
     * @return
     */
    @Override
    public List<Menu> selectAllMues(Integer id) {
        return menuMapper.getMenusByAdminId(id);
    }

    /**
     * 查询该角色所局域的权限进行回显
     */
    @Override
    public List<Integer> selectMuesByRid(Integer rid) {
        List<Integer> mues = menuMapper.selectMuesById(rid);
        return mues;
    }

    /**
     * 修改角色权限
     * @param rid 该角色ID
     * @param mids 该角色要修改的权限的ID
     * @return
     */
    @Transactional
    @Override
    public RespBean updateMenus(Integer rid, Integer[] mids) {
        //判断角色ID是否为空
        if(null == rid){
            return RespBean.error("数据修改失败");
        }
        //查询当前角色权限是否有值
        Integer menusCount = this.selectMenusRole(rid);
        //判断是否有值
        if(menusCount > 0){
            //调用删除方法
            int count = menuMapper.deleteMenusRole(rid);
            //判断返回的影响行数
            if(count < 1){
                return RespBean.error("数据修改失败");
            }
        }
        //判断数据是否为空
        if(mids.length < 0){
            return RespBean.error("请选择角色权限");
        }
        //插入角色和权限中间表数据
        int result = menuMapper.insertMenusRole(rid,mids);
        //判断影响行数
        if(result != mids.length){
            return RespBean.error("数据修改失败");
        }
        return RespBean.success("数据修改成功");
    }

    /**
     * 查询当前角色是否有权限
     * @param rid
     * @return
     */
    private Integer selectMenusRole(Integer rid){
        Integer count = menuMapper.selectCountByRid(rid);
        return count;
    }
}
