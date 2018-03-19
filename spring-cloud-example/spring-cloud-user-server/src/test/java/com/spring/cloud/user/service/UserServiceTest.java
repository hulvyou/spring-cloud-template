package com.spring.cloud.user.service;

import com.spring.cloud.user.domain.UserDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户业务测试
 *
 * @author Travel Hu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findByIdTest() {
        UserDO userDO = userService.findById(1L);
        Assert.assertEquals("admin", userDO.getUsername());
    }
}
