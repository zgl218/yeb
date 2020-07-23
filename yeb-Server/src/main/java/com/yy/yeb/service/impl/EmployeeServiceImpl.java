package com.yy.yeb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.yeb.pojo.Employee;
import com.yy.yeb.mapper.EmployeeMapper;
import com.yy.yeb.query.EmployeeQuery;
import com.yy.yeb.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> queryEmployeeForTable(EmployeeQuery employeeQuery) {
        Map<String,Object> result = new HashMap<String,Object>();
        PageHelper.startPage(employeeQuery.getCurrentPage(),employeeQuery.getSize());
        PageInfo pageInfo =new PageInfo(selectByParams(employeeQuery));
        result.put("count",pageInfo.getTotal());
        result.put("data",pageInfo.getList());
//        result.put("code",0);
//        result.put("msg","");
        return result;
    }

    @Override
    public Employee queryEmployeeByName(String name) {
        return employeeMapper.queryEmployeeByName(name);
    }

    @Override
    public int deletePositionById(Integer id) {
        return employeeMapper.deleteById(id);
    }

    private List<Employee> selectByParams(EmployeeQuery employeeQuery) throws DataAccessException {
        return  employeeMapper.selectEmployeeAll( );
    }

}
