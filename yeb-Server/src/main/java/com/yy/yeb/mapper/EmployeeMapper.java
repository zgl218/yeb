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

//         处理关联员工   职位删除将相关职位员工职位信息设置为null
    int updateEmployeeByPosId(Integer id);

//         处理关联员工   职位删除将相关职位员工职位信息设置为null
    int updateEmployeeByPosIds(String[] ids);

//         处理关联员工   职位删除将相关职位员工职称信息设置为null
    int updateEmployeeByJobLevelId(Integer id);
//         处理关联员工   职位删除将相关职位员工职称信息设置为null
    int updateEmployeeByJobLevelIds(String[] ids);
    /**
     * 根据单个职称id查询关联员工数量
     * @param id
     * @return
     */
    int getNumByEmpJoblevleId(Integer id);

    /**
     * 根据多个职称id查询关联员工数量
     * @param ids
     * @return
     */
    int getNumByEmpJoblevleIds(String[] ids);

    /**
     * 根据单个职位id查询关联员工数量
     * @param id
     * @return
     */
    int getNumByEmpPosId(Integer id);
    /**
     * 根据多个职位id查询关联员工数量
     * @param ids
     * @return
     */
    int getNumByEmpPosIds(String[] ids);
}
