package com.yy.yeb.controller;


import com.yy.yeb.pojo.Department;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.service.IDepartmentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "部门添加")
    @PostMapping("/department")
    public RespBean addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }




}
