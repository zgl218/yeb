package com.yy.yeb.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * CreateTime: 2020/07/23-15:35    author: Guangli Zhang
 * description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="密码实体类", description="")
public class PassWordDetail {
    @ApiModelProperty(value = "旧密码")
    @Getter
    @Setter
    private String oldPass;

    @ApiModelProperty(value = "新密码")
    @Getter@Setter
    private String pass;

    @ApiModelProperty(value = "确认密码")
    @Getter@Setter
    private String checkPass;

    @ApiModelProperty(value = "id")
    @Getter@Setter
    private Integer adminId;

}
