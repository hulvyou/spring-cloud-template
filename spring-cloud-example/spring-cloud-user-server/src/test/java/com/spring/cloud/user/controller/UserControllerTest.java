package com.spring.cloud.user.controller;

import com.spring.cloud.user.domain.UserDetailDO;
import com.spring.cloud.user.dto.UserDetailDTO;
import com.spring.cloud.user.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户接口mock测试
 *
 * @author Travel Hu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest {

    //被注入mock对象的类
    @InjectMocks
    private UserController userController = new UserController();

    //需要mock的bean
    @Mock
    private UserService userService;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void queryUserDetailTest() {
        UserDetailDO userDetailDO = new UserDetailDO();
        userDetailDO.setUsername("test");
        Mockito.when(userService.queryUserDetail(anyString())).thenReturn(userDetailDO);

        UserDetailDTO userDetailDTO = userController.queryUserDetail("test");
        Assert.assertEquals("test", userDetailDTO.getUsername());
    }

    @Test
    public void queryUserDetailRestfulTest() throws Exception{
        UserDetailDO userDetailDO = new UserDetailDO();
        userDetailDO.setUsername("test");
        Mockito.when(userService.queryUserDetail(anyString())).thenReturn(userDetailDO);

        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/test").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
