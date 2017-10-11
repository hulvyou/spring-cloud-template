package com.spring.cloud.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 权限信息
 *
 * @author Travel Hu
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "权限信息")
public class AuthorityDTO implements Serializable{
    private static final long serialVersionUID = -4682584712969987759L;

    @ApiModelProperty(value = "权限ID", dataType = "Integer")
    private Long authorityId;
    @ApiModelProperty(value = "父ID", dataType = "Integer")
    private Long parentId;
    @ApiModelProperty(value = "权限名称", dataType = "String")
    private String authorityName;
    @ApiModelProperty(value = "权限资源", dataType = "String")
    private String authority;
    @ApiModelProperty(value = "权限code", dataType = "String")
    private String code;
    @ApiModelProperty(value = "层级", dataType = "String")
    private Integer level;
    @ApiModelProperty(value = "排序", dataType = "String")
    private Integer sort;
}
