package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.pojo.AdminUserDetail;
import com.yy.yeb.pojo.PassWordDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface AdminMapper extends BaseMapper<Admin> {
        /**
         * 初始化界面 查询
         * @param keywords
         * @return
         */
        List<Admin> operatingTheAdmin(String keywords);

        /**
         * 关闭用户
         * @param adminId
         * @return
         */
        Integer enabledChangeCloseById(Integer adminId);

        /**
         *  开启用户
         * @param adminId
         * @return
         */
        Integer enabledChangeOpenById(Integer adminId);

        /**
         * 根据用户id删除用户
         * @param adminId
         * @return
         */
        Integer deleteAdminById(Integer adminId);

        String selectPasswordById(Integer adminId);

        Integer updatePassword(PassWordDetail passWordDetail);

        Integer updateByDetail(AdminUserDetail adminUserDetail);
}
