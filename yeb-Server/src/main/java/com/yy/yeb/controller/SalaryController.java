package com.yy.yeb.controller;


import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Salary;
import com.yy.yeb.service.ISalaryService;
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
@RequestMapping("/salary")
public class SalaryController {
    @Resource
    private ISalaryService salaryService;

    /**
     * 查询薪资账套
     * @return
     */

    @GetMapping("/sob")
    public List<Salary> querySalary(){
        return salaryService.querySalary();
    }

    /**
     * 添加薪资账套
     * @param salary
     * @return
     */
    @PostMapping("/sob")
    public RespBean addSalary(Salary salary){
        return salaryService.addSalary(salary);
    }

    /**
     * 更改薪资账套
     * @param salary
     * @return
     */
    @PutMapping("/sob")
    public RespBean updateSalary(@RequestBody Salary salary){
        return salaryService.updateSalary(salary);
    }

    /**
     * 删除薪资账套
     * @param id
     * @return
     */
    @DeleteMapping("/sob/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        return salaryService.deleteSalaryById(id);
    }

    /**
     *删除多个薪资账套
     * @param ids
     * @return
     */
    @DeleteMapping("/sob")
    public RespBean deleteSalaryById(String[] ids){
        return salaryService.deleteSalaryByIds(ids);
    }
}
