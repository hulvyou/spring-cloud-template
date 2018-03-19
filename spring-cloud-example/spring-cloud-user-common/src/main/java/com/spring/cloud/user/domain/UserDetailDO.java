package com.spring.cloud.user.domain;

import lombok.*;

import java.util.List;

/**
 * @author Travel Hu
 */
@Data
public class UserDetailDO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String secret;

    /**
     * 角色列表
     */
    private List<RoleDO> roleList;
}
