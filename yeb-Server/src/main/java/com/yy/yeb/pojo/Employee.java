package com.yy.yeb.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangyu
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_employee")
@ApiModel(value="Employee对象", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String workID;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "婚姻状况")
    private String wedlock;

    @ApiModelProperty(value = "民族")
//    @TableField(exist = false)
//    private String nationId;
    private String nationName;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌")
//    @TableField(exist = false)
//    private String politicId;
    private String politicsStatusName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "所属部门")
//    @TableField(exist = false)
//    private String departmentId;

    private String departmentName;

    @ApiModelProperty(value = "职称ID")
//    @TableField(exist = false)
//    private String jobLevelId;
    private String joblevelName;

    @ApiModelProperty(value = "职位ID")
//    @TableField(exist = false)
//    private String posId;
    private String positionName;

    @ApiModelProperty(value = "聘用形式")
    private String engageForm;

    @ApiModelProperty(value = "最高学历")
    private String tiptopDegree;


    @ApiModelProperty(value = "毕业院校")
    private String school;

    @ApiModelProperty(value = "所属专业")
    private String specialty;

    @ApiModelProperty(value = "在职状态")
    private String workState;

    @ApiModelProperty(value = "入职日期")
    private LocalDate beginDate;

    @ApiModelProperty(value = "转正日期")
    private LocalDate conversionTime;

    @ApiModelProperty(value = "离职日期")
    private LocalDate notWorkDate;

    @ApiModelProperty(value = "合同起始日期")
    private LocalDate beginContract;

    @ApiModelProperty(value = "合同终止日期")
    private LocalDate endContract;

    @ApiModelProperty(value = "合同期限")
    private Double contractTerm;

}
