package com.spring.cloud.user.controller;

import com.spring.cloud.user.domain.UserDetailDO;
import com.spring.cloud.user.dto.UserDetailDTO;
import com.spring.cloud.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Travel Hu
 */
@RestController
@Api(value = "/api", description = "用户接口")
@RequestMapping("/api")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/v1/users/{username}")
    @ApiOperation(value = "获取用户详情信息", httpMethod = "GET", response = UserDetailDTO.class, notes = "获取用户详情信息")
    public UserDetailDTO queryUserDetailList(@ApiParam(required = true, name = "username", value = "用户名") @PathVariable("username") String username) {
        LOGGER.info("获取用户详情信息 username:{}", username);
        UserDetailDO userDetailDO = userService.queryUserDetailList(username);

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        BeanUtils.copyProperties(userDetailDO, userDetailDTO);
        return userDetailDTO;
    }
}