package com.spring.cloud.user.service;

import com.spring.cloud.user.dao.UserMapper;
import com.spring.cloud.user.domain.UserDO;
import com.spring.cloud.user.domain.UserDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息业务
 *
 * @author Travel Hu
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户详情
     *
     * @param username
     * @return
     */
    public UserDetailDO queryUserDetail(String username) {
        return userMapper.queryUserDetail(username);
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id
     * @return
     */
    public UserDO findById(Long id) {
        return userMapper.queryById(id);
    }
}
