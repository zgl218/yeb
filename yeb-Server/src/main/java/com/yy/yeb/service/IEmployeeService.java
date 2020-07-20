package com.yy.yeb.service;

import com.yy.yeb.pojo.Employee;
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
public interface IEmployeeService extends IService<Employee> {

    /**
     * 查询所有员工信息
     *
     * @return 员工信息列表
     */
    public List<Employee> selectEmployeeAll();
}
