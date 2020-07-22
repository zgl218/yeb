package com.yy.yeb.controller;
import com.yy.yeb.pojo.Employee;
import com.yy.yeb.query.EmployeeQuery;
import com.yy.yeb.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "多条件分页查询员工列表")
    @GetMapping("/")
    public Map<String,Object> queryEmployeeByParams(EmployeeQuery employeeQuery){
        return employeeService.queryEmployeeForTable(employeeQuery);
    }


//    @ApiOperation(value = "通过员工名查询员工信息")
//    @GetMapping("/")
//    public Employee queryEmployeeByName(String name){
//        return employeeService.queryEmployeeByName(name);
//    }

    /**
     *根据id删除员工信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除员工信息")
    @DeleteMapping("/")
    public int deleteEmployeeById(@PathVariable Integer id) {
        return employeeService.deletePositionById(id);
    }




}
