package com.yy.yeb.controller;


import com.yy.yeb.pojo.Employee;
import com.yy.yeb.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 获取员工信息
     */
    @ApiOperation(value = "员工信息查询")
    @GetMapping("/basic")
    public List<Employee> selectEmployeeAll(){
        return employeeService.selectEmployeeAll();
    }

}
