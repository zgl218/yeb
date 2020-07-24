package com.yy.yeb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.yeb.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.yeb.query.QueryEmployee;
import org.apache.ibatis.annotations.Param;

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

    //处理关联员工
    void updateEmployeeSalaryId(Integer id);

    //处理多名关联员工
    void updateEmploySalaryIds(String[] ids);


    /**
     * 通过员工名查询员工对象
     * @param name
     * @return
     */
//    Employee queryEmployeeByName(String name);

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


    String selectMaxWorkId();

    Employee selectByName(String name);

    IPage<Employee> selectEmployeeAll(@Param("page") Page page, @Param("queryEmployee") QueryEmployee queryEmployee);

    Integer insertEmployeeByEmp(Employee employee);

    Integer updateEmployeeByEmp(Employee employee);

//    int selectCountEmp();
//    List<Map<String, Object>> queryAllEmployee();
}
