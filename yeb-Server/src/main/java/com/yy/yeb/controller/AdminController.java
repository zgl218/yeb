package com.yy.yeb.controller;


import com.yy.yeb.pojo.Admin;
import com.yy.yeb.pojo.AdminUserDetail;
import com.yy.yeb.pojo.PassWordDetail;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IAdminService;
import com.yy.yeb.service.impl.AdminServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "修改信息")
    @PutMapping("/info")
    public RespBean updateAdmin(@RequestBody AdminUserDetail adminUserDetail){
        System.out.println();
        return adminService.updateAdmin(adminUserDetail);
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/pass")
    public RespBean updatePass(@RequestBody PassWordDetail passwordInfo){

        return adminService.updatePass(passwordInfo);
    }
}
