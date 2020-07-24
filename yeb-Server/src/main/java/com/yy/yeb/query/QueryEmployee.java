package com.yy.yeb.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.yeb.pojo.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 *
 * @author xingtong
 */
@Data
@Getter
@Setter
public class QueryEmployee  {

    private String name;

    private int currentPage = 1;

    private int size=10;
    // 政治面貌
    private Integer politicId;
    //所属部门
    private Integer departmentId;
    //民族
    private Integer nationId;
    //职位
    private Integer posId;
    //职称
    private Integer jobLevelId;
    //聘用形式
    private String engageForm;
    //入职日期    格式: 2020-07-22 , 2020-08-18  范围里
    private String beginDateScope;

    private String beginTime;

    private String endTime;

}