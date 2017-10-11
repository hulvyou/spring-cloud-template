package com.spring.cloud.user.dao;

import com.spring.cloud.user.domain.UserDetailDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Travel Hu
 */
public interface UserMapper {

    /**
     * 获取用户详情
     *
      * @param username
     * @return
     */
    UserDetailDO queryUserDetailList(@Param("username") String username);
}
