package com.spring.cloud.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 角色信息
 *
 * @author Travel Hu
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "角色信息")
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = -8919384868843829855L;

    @ApiModelProperty(value = "角色ID", dataType = "Integer")
    private Long roleId;
    @ApiModelProperty(value = "角色名称", dataType = "String")
    private String roleName;

    @ApiModelProperty(value = "权限列表", dataType = "List")
    private List<AuthorityDTO> authorityList;
}
