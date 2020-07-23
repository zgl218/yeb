package com.yy.yeb.controller;


import com.yy.yeb.pojo.Employee;
import com.yy.yeb.service.IEmployeeService;
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
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

}
