package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Employee;
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
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> selectEmployeeAll();
}
