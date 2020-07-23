package com.yy.yeb.service;

import com.yy.yeb.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.query.EmployeeQuery;
import java.util.Map;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IEmployeeService extends IService<Employee> {
    Map<String, Object> queryEmployeeForTable(EmployeeQuery employeeQuery);


    /**
     * 通过员工名查询员工对象
     * @param name
     * @return
     */
    Employee queryEmployeeByName(String name);

    /**
     * 通过员工id删除员工信息
     * @param id
     * @return
     */
    int deletePositionById(Integer id);
}
