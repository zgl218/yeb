package com.yy.yeb.controller;


import com.yy.yeb.pojo.Department;
import com.yy.yeb.service.IDepartmentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/system/basic")
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;

    @ApiOperation(value = "部门查询")
    @GetMapping("/department")
    public List<Department> queryAllDepartment() {

        return departmentService.queryAllDepartment();
    }
}
