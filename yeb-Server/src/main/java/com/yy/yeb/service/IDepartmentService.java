package com.yy.yeb.service;

import com.yy.yeb.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.yeb.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
public interface IDepartmentService extends IService<Department> {

//    查询部门
    List<Department> queryAllDepartment();

//    添加部门
    RespBean addDepartment(Department department);

}
