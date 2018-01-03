package com.spring.cloud.user.domain;

import lombok.*;

import java.util.Date;

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
public class UserDO {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String secret;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
