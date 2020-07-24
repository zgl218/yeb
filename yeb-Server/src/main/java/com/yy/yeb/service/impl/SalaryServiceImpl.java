package com.yy.yeb.service.impl;

import com.yy.yeb.mapper.EmployeeMapper;
import com.yy.yeb.pojo.RespBean;
import com.yy.yeb.pojo.Salary;
import com.yy.yeb.mapper.SalaryMapper;
import com.yy.yeb.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {
    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 查询薪资账套
     *
     *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              @return
     */
    @Override
    public List<Salary> querySalary() {
        return salaryMapper.querySalary();
    }

    /**
     * 添加薪资账套
     *
     * @param salary
     * @return
     */

    @Override
    public RespBean addSalary(Salary salary) {
        if (salary == null) {
            return RespBean.error("请输入");
        } else {
            Salary salaryByName = salaryMapper.querySalaryByName(salary.getName());
            if (salaryByName != null) {
                return RespBean.error("该薪资账套已存在");
            } else {
                // LocalDateTim 4 e.now(Clock.system(ZoneId.of("Asia/shanghai")));
                salary.setCreateDate(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
                int sal1 = salaryMapper.addSalary(salary);
                if (sal1 == 1) {
                    return RespBean.success("薪资账套添加成功");
                } else {
                    return RespBean.error("薪资账套添加失败");
                }
            }
        }


    }
    /**
     * 修改薪资账套
     * @param salary
     * @return
     */

    @Override
    public RespBean updateSalary (Salary salary) {
        System.out.println(salary);
        if (salary == null) {
            return RespBean.error("请修改");
        } else {
            Salary salaryByName =  salaryMapper.querySalaryByName(salary.getName());
            LocalDateTime now =  LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
            System.out.println(now);
            salary.setCreateDate(salary.getCreateDate());
            salary.setId(salary.getId());
            System.out.println("============================="+salary);
            int i;
            if(salaryByName == null){
                i = salaryMapper.updateSalary(salary);
                return getRespBeanWithUpte(i,"更改成功");
            }
            else if (salaryByName.getId() != salary.getId() && salaryByName!= null){
                return RespBean.error("改薪资账套已存在");
            }
            else {
                i = salaryMapper.updateById(salary);
                return getRespBeanWithUpte(i,"更新成功啦");
            }


        }
    }

    private RespBean getRespBeanWithUpte(int i,String s){
        if(i ==1){
            return RespBean.success(s);
        }
        else {
            return RespBean.error("更改失败");
        }
    }

    /**
     * 删除薪资账套
     * @param id
     * @return
     */
    @Override
    public RespBean deleteSalaryById(Integer id) {
        if(id == null){
            return RespBean.error("请选择");
        }else {

            employeeMapper.updateEmployeeSalaryId(id);

            int i = salaryMapper.deleteSalaryById(id);

            if (i ==1){
                return  RespBean.success("删除成功");
            }else {
                return  RespBean.error("删除失败");
            }
        }
    }

    /**
     * 删除多个薪资账套
     * @param ids
     * @return
     */
    @Override
    public RespBean deleteSalaryByIds(String[] ids) {
        if (ids == null || ids.length ==0){
            return RespBean.error("请选择");
        }else {

            employeeMapper.updateEmploySalaryIds(ids);

            int i = salaryMapper.deleteSalaryByIds(ids);
            if (i ==1){
                return  RespBean.success("删除成功");
            }else {
                return  RespBean.error("删除失败");
            }
        }
    }
}
