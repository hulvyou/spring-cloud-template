package com.spring.cloud.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author Travel Hu
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "用户信息")
public class UserDetailDTO implements Serializable{
    private static final long serialVersionUID = 3404238745747550127L;

    @ApiModelProperty(value = "用户ID", dataType = "Integer")
    private Long userId;
    @ApiModelProperty(value = "用户名", dataType = "String")
    private String username;
    @ApiModelProperty(value = "密码", dataType = "String")
    private String secret;

    @ApiModelProperty(value = "角色列表", dataType = "List")
    private List<RoleDTO> roleList;
}
