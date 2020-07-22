package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.Role;
import com.yy.yeb.mapper.RoleMapper;
import com.yy.yeb.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> getRoles(){
        return roleMapper.getRoles(1);
    }

}
