package com.spring.cloud.user.api;

import com.spring.cloud.user.dto.UserDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private int port;
    @Test
    public void findUserByIdTest(){
        System.out.println(port);
        /*RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:8080/api/v1/users/1",String.class);
        System.out.println(result);*/
        UserDetailDTO userDetailDTO = userService.findUserByUsername("admin");
        System.out.println(userDetailDTO.toString());
    }
}
