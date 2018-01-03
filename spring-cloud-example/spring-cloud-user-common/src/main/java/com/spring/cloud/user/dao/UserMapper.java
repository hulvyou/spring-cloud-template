package com.spring.cloud.user.dao;

import com.spring.cloud.user.domain.UserDO;
import com.spring.cloud.user.domain.UserDetailDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    UserDetailDO queryUserDetail(@Param("username") String username);

    /**
     * 添加用户
     *
     * @param userDO
     */
    void addUser(UserDO userDO);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 修改用户
     *
     * @param userDO
     */
    void updateUser(UserDO userDO);

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    UserDO queryById(Long id);

    /**
     * 查询用户列表
     *
     * @return
     */
    List<UserDO> queryUserList();
}
