package com.spring.cloud.user.service;

import com.spring.cloud.user.dao.UserMapper;
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

    public UserDetailDO queryUserDetailList(String username) {
        return userMapper.queryUserDetailList(username);
    }
}
