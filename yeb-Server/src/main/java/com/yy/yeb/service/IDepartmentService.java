package com.yy.yeb.service;

import com.yy.yeb.pojo.Department;
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
public interface IDepartmentService extends IService<Department> {

    List<Department> queryAllDepartment();
}
