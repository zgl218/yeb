package com.yy.yeb.service;

import com.yy.yeb.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.query.QueryEmployee;

import java.util.List;
import java.util.Map;

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
     * 员工信息页面展示
     *
     * @param queryEmployee
     * @return
     */
    Map<String, Object> queryEmployeeForTable(QueryEmployee queryEmployee);

    /**
     * 获得工作ID
     * @return
     */
    RespBean getMaxWorkID();

    /**
     * 查询所有职位
     * @return
     */
    List<Position> initPositions();

    /**
     * 查询所有民族
     * @return
     */
    List<Nation> initNations();

    /**
     * 查询所有职称
     * @return
     */
    List<Joblevel> initJoblevels();


    /**
     * 查询所有政治面貌
     * @return
     */
    List<PoliticsStatus> initPoliticsstatus();

    /**
     * 查询所有部门
     * @return
     */
    List<Department> initDepartments();


    /**
     * 新增员工信息
     * @param employee
     */
    RespBean InsertEmployee(Employee employee);

    /**
     * 删除员工信息
     * @param id
     */
    RespBean deleteEmployee(Integer id);

    /**
     * 修改员工信息
     * @param employee
     */
    RespBean UpdateEmployee(Employee employee);
}
