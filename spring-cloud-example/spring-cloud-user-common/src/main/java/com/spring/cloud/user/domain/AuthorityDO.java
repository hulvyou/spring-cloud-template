package com.spring.cloud.user.domain;

import lombok.*;

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
public class AuthorityDO {
    /**
     * 权限ID
     */
    private Long authorityId;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 权限名称
     */
    private String authorityName;
    /**
     * 权限资源
     */
    private String authority;
    /**
     * 权限code
     */
    private String code;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 排序
     */
    private Integer sort;
}
