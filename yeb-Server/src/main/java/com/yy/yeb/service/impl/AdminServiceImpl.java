package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.yeb.config.security.JwtTokenUtil;
import com.yy.yeb.mapper.AdminMapper;
import com.yy.yeb.mapper.RoleMapper;
import com.yy.yeb.pojo.*;
import com.yy.yeb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xingtong
 * @since 2020-07-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private RoleMapper roleMapper;


    /**
     * 登录成功返回token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code,HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isBlank(code) || !captcha.equals(code)) {
            return RespBean.error("验证码填写错误！");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //判断用户名或密码是否正确
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        //判断用户是否被禁用
        if (!userDetails.isEnabled()) {
            return RespBean.error("用户被禁用，请联系管理员！");
        }
        //更新security上下文的用户对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //生成token并返回
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
    }



    @Override
    public RespBean updateAdmin(AdminUserDetail adminUserDetail) {
        // 参数校验
//        用户名校验
        if (StringUtils.isBlank(adminUserDetail.getName())) {
            return RespBean.error("用户名不能为空！！！");
        }
//        电话号码校验
        if (StringUtils.isBlank(adminUserDetail.getTelephone())) {
            return RespBean.error("电话号码不能为空！！！");
        }
//        手机号码校验
        if (StringUtils.isBlank(adminUserDetail.getPhone())) {
            return RespBean.error("手机号码不能为空！！！");
        }
//        用户地址校验
        if (StringUtils.isBlank(adminUserDetail.getAddress())) {
            return RespBean.error("用户地址不能为空！！！");
        }
        //执行修改操作
        if (adminMapper.updateByDetail(adminUserDetail) < 1) {
            return RespBean.error("修改失败！！！");
        } else {
            return RespBean.success("修改成功！！！");
        }
    }
    @Override
    public RespBean updatePass(PassWordDetail passWordDetail) {
        //参数校验
        if(StringUtils.isBlank(passWordDetail.getOldPass())){
            return RespBean.error("旧密码不能为空！！！");
        }
        if(StringUtils.isBlank(passWordDetail.getPass())){
            return RespBean.error("新密码不能为空！！！");
        }
        if(StringUtils.isBlank(passWordDetail.getCheckPass())){
            return RespBean.error("确认密码不能为空！！！");
        }
        //核对旧密码是否正确
        String oldPassword = passWordDetail.getOldPass();
        if(!(passwordEncoder.matches(oldPassword,adminMapper.selectPasswordById(passWordDetail.getAdminId())))){
            return RespBean.error("请输入正确的旧密码！！！");
        }
        //新密码与旧密码不能相同
        String newPassword = passWordDetail.getPass();
        if(newPassword.equals(oldPassword)){
            return RespBean.error("新密码与旧密码不能相同！！！");
        }
        //确认密码不一致
        String checkPassword = passWordDetail.getCheckPass();
        if(!(checkPassword.equals(newPassword))){
            return RespBean.error("您输入的密码不一致！！！");
        }
        checkPassword = passwordEncoder.encode(checkPassword);
        passWordDetail.setCheckPass(checkPassword);
        if(adminMapper.updatePassword(passWordDetail) < 1){
            return RespBean.error("密码修改失败！！！");
        }
        return RespBean.success("密码修改成功！！！");

    }
}
