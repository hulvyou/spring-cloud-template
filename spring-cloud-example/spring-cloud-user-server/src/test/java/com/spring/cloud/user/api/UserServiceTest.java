package com.spring.cloud.user.api;

import com.spring.cloud.user.dto.UserDetailDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户接口测试
 *
 * @author Travel Hu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findUserByUsernameTest() {
        UserDetailDTO userDetailDTO = userService.findUserByUsername("admin");
        Assert.assertEquals("admin", userDetailDTO.getUsername());
    }
}
