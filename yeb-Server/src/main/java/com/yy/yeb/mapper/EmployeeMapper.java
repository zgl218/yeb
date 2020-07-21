package com.yy.yeb.mapper;

import com.yy.yeb.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

//         处理关联员工   职位删除将相关职位员工职位信息设置为null
    void updateEmployeeByPosId(Integer id);

//         处理关联员工   职位删除将相关职位员工职位信息设置为null
    void updateEmployeeByPosIds(String[] ids);

//         处理关联员工   职位删除将相关职位员工职称信息设置为null
    void updateEmployeeByJobLevelId(Integer id);
//         处理关联员工   职位删除将相关职位员工职称信息设置为null
    void updateEmployeeByJobLevelIds(String[] ids);
}
