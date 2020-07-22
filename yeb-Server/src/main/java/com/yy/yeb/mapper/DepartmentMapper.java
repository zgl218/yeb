package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface DepartmentMapper extends BaseMapper<Department> {

//    查询所有部门
    List<Department> queryAllDepartment();

    int addDepartment(Department department);
}
