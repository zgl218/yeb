package com.yy.yeb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.yeb.mapper.*;
import com.yy.yeb.pojo.*;
import com.yy.yeb.query.QueryEmployee;
import com.yy.yeb.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.yeb.utils.AddStringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Resource
    private NationMapper nationMapper;

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private PoliticsStatusMapper politicsStatusMapper;

    @Resource
    private PositionMapper positionMapper;


    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");


//    /**
//     * 员工信息页面展示
//     * @param queryEmployee
//     * @return
//     */
//    @Override
//    public Map<String, Object> queryEmployeeForTable(QueryEmployee queryEmployee) {
//        Map<String,Object> result = new HashMap<String,Object>();
//        PageHelper.startPage(queryEmployee.getCurrentPage(),queryEmployee.getSize());
//        PageInfo pageInfo =new PageInfo(selectByParams(queryEmployee));
//        result.put("total",pageInfo.getTotal());
//        result.put("data",pageInfo.getList());
//        return result;
//    }


    /**
     * 员工信息页面展示
     * @param queryEmployee
     * @return
     */
    @Override
    public Map<String, Object> queryEmployeeForTable(QueryEmployee queryEmployee) {
        Page<Employee> page = new Page<>(queryEmployee.getCurrentPage(),queryEmployee.getSize());
        Map<String,Object> map = new HashMap<String, Object>();
        if (queryEmployee.getBeginDateScope() != null && !queryEmployee.getBeginDateScope().equals("")){
            String[] time = queryEmployee.getBeginDateScope().split(",");
            String beginTime = time[0];
            String endTime = time[1];
            queryEmployee.setBeginTime(beginTime);
            queryEmployee.setEndTime(endTime);
        }
        IPage<Employee> iPage = employeeMapper.selectEmployeeAll(page,queryEmployee);
        map.put("data",iPage.getRecords());
        map.put("total",iPage.getTotal());
        return map;
    }

    /**
     * InsertEmployee 添加员工信息
     * @param employee
     */
    @Override
    public RespBean InsertEmployee(Employee employee) {
        //非空校验
//        AssertUtil.isTrue(employeeMapper.insert(employee)<1,"添加失败！");
        Employee employee1 = checkContractTerm(employee);
        Integer num = employeeMapper.insertEmployeeByEmp(employee1);
        if (num == null || num == 0) {
            return RespBean.error("系统异常,添加失败");
        }
        return RespBean.success("添加成功");
    }

    /**
     * 删除员工信息
     *
     * @param id
     */
    @Override
    public RespBean deleteEmployee(Integer id) {
        //非空校验
//        AssertUtil.isTrue(id == null,"待删除的记录不存在！");
//        Employee employee = employeeMapper.selectById(id);
//        AssertUtil.isTrue(employee == null,"待删除的记录不存在！");
//        AssertUtil.isTrue(employeeMapper.deleteById(id)<1,"删除失败了");
        List<Integer> list = new ArrayList<>();
        list.add(id);
        int num = employeeMapper.deleteBatchIds(list);
        if (num == 0) {
            return RespBean.error("系统异常,删除失败");
        }
        return RespBean.success("删除成功");
    }


    /**
     * 修改员工信息
     *
     * @param employee
     */
    @Override
    public RespBean UpdateEmployee(Employee employee) {
//        System.out.println(employee.toString());
//        Integer id = employee.getId();
//        AssertUtil.isTrue(id == null || employeeMapper.selectById(id)==null,"待修改的用户记录不存在！");
//        //非空校验
//        AssertUtil.isTrue(employeeMapper.update(employee,null)<1,"修改失败了！");
        Employee employee1 = checkContractTerm(employee);
        Integer num = employeeMapper.updateEmployeeByEmp(employee1);
        if (num == null || num == 0) {
            return RespBean.error("系统异常,更新失败");
        }
        return RespBean.success("编辑成功");
    }


    /**
     * 查询所有的工作职位
     * @return
     */
    @Override
    public List<Position> initPositions() {
        return positionMapper.selectList(null);
    }

    /**
     * 查询所有的民族
     * @return
     */
    @Override
    public List<Nation> initNations() {
        return nationMapper.selectList(null);
    }

    /**
     * 查询所有的职称
     * @return
     */
    @Override
    public List<Joblevel> initJoblevels() {
        return joblevelMapper.selectList(null);
    }

    /**
     * 查询所有的政治面貌
     * @return
     */
    @Override
    public List<PoliticsStatus> initPoliticsstatus() {
        return politicsStatusMapper.selectList(null);
    }

    /**
     * 查询所有的部门
     * @return
     */
    @Override
    public List<Department> initDepartments() {
        return departmentMapper.selectList(null);
    }


    /**
     * 获取工作ID
     *
     * @return
     */
    @Override
    public RespBean getMaxWorkID() {
        RespBean respBean = new RespBean();
        String workId = AddStringUtil.addOne(employeeMapper.selectMaxWorkId());
        respBean.setObj(workId);
        return respBean;
    }



    private Employee checkContractTerm(Employee employee) {
        // 计算日期差值
        long lnum = employee.getEndContract().toEpochDay() - employee.getBeginContract().toEpochDay();
        // 保留两位小数
        BigDecimal b = new BigDecimal((double) lnum / 365);
        Double contractTerm = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        employee.setContractTerm(contractTerm);
        return employee;
    }

}
