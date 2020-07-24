package com.yy.yeb.service;

import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface ISalaryService extends IService<Salary> {
    //查询薪资账套
    List<Salary> querySalary();

    //添加薪资账套
    RespBean addSalary(Salary salary);

    //修改薪资账套
    RespBean updateSalary(Salary salary);

    //删除薪资账套
    RespBean deleteSalaryById(Integer id);

    //删除多个薪资账套
    RespBean deleteSalaryByIds(String[] ids);
}
