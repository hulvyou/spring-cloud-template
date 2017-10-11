package com.spring.cloud.user.domain;

import lombok.*;

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
public class RoleDO{

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 权限列表
     */
    private List<AuthorityDO> authorityList;
}
