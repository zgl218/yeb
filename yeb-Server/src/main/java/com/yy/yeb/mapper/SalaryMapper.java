package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface SalaryMapper extends BaseMapper<Salary> {
    //查询薪资账套
    List<Salary> querySalary();

    //添加薪资账套
    int addSalary(Salary salary);

    //修改薪资账套
    int updateSalary(Salary salary);

    //根据名字查询薪资账套
    Salary querySalaryByName(String name);

    //删除薪资账套
    int deleteSalaryById(Integer id);

    //删除多条薪资账套
    int deleteSalaryByIds(String[] ids);
}
