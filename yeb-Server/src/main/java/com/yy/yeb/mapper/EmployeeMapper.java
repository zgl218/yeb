package com.yy.yeb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.yeb.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.query.EmployeeQuery;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 通过员工名查询员工对象
     * @param name
     * @return
     */
    Employee queryEmployeeByName(String name);

//    Employee queryEmployeeByParams(String )
    /**
     * 查询 : 根据id查询员工列表，分页显示
     *
     * @param
     * @param
     * @return
     */
//    IPage<Employee> selectEmployeeAll(Page<?> page,Integer id);

//    Object selectEmployeeAll(EmployeeQuery employeeQuery);

    List<Employee> selectEmployeeAll();
//    List<Map<String, Object>> queryAllEmployee();
}
