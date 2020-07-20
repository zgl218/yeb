package com.yy.yeb.service.impl;

import com.yy.yeb.pojo.Employee;
import com.yy.yeb.mapper.EmployeeMapper;
import com.yy.yeb.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> selectEmployeeAll() {
        return employeeMapper.selectEmployeeAll();
    }
}
