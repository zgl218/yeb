package com.yy.yeb.controller;


import com.yy.yeb.pojo.*;
import com.yy.yeb.query.QueryEmployee;
import com.yy.yeb.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;


    /**
     * 员工信息页面展示
     * @param queryEmployee
     * @return
     */
    @ApiOperation(value = "员工信息页面展示")
    @GetMapping("/basic")
    public Map<String,Object> queryEmployeeByParams(QueryEmployee queryEmployee){
        return employeeService.queryEmployeeForTable(queryEmployee);
    }

    /**
     * 获得工作ID
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/basic/maxWorkID")
    public RespBean getMaxWorkID(){
        return employeeService.getMaxWorkID();
    }


    /**
     * 查询所有职位
     * @return
     */
    @ResponseBody
    @RequestMapping("/basic/positions")
    public List<Position> initPositions(){
        return employeeService.initPositions();
    }
    /**
     * 查询所有民族
     * @return
     */
    @ResponseBody
    @RequestMapping("/basic/nations")
    public List<Nation> initNations(){
        return employeeService.initNations();
    }

    /**
     * 查询所有的职称
     * @return
     */
    @ResponseBody
    @GetMapping("/basic/joblevels")
    public List<Joblevel> initJoblevels() {
        return employeeService.initJoblevels();
    }

    /**
     * 查询所有的政治面貌
     * @return
     */
    @ResponseBody
    @GetMapping("/basic/politicsstatus")
    public List<PoliticsStatus> initPoliticsstatus() {

        return employeeService.initPoliticsstatus();
    }

    /**
     * 查询所有的部门
     * @return
     */
    @ResponseBody
    @GetMapping("/basic/deps")
    public List<Department> initDepartments(){
        return employeeService.initDepartments();
    }


    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    @ApiOperation(value = "新增员工信息")
    @ResponseBody
    @PostMapping("/basic")
    public RespBean InsertEmployee(@RequestBody Employee employee){
        return employeeService.InsertEmployee(employee);
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @ApiOperation(value = "修改员工信息")
    @ResponseBody
    @PutMapping("/basic")
    public RespBean UpdateEmployee(@RequestBody Employee employee){
        return employeeService.UpdateEmployee(employee);
    }

    /**
     * 删除员工信息
     * @param employee
     * @return
     */
    @ApiOperation(value = "删除员工信息")
    @ResponseBody
    @DeleteMapping("/basic/{id}")
    public RespBean deleteEmployee(Employee employee){
        return employeeService.deleteEmployee(employee.getId());
    }

}
