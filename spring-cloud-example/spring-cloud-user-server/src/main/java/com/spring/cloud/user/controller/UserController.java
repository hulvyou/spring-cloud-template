package com.spring.cloud.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.user.service.UserService;
import com.spring.cloud.user.domain.UserDetailDO;
import com.spring.cloud.user.dto.UserDetailDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /***
     * 获取用户详情信息-直接对象响应
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/v1/users/{username}")
    @ApiOperation(value = "获取用户详情信息", httpMethod = "GET", response = UserDetailDTO.class, notes = "获取用户详情信息-直接对象响应")
    @HystrixCommand(fallbackMethod = "queryUserDetailFallback")
    public UserDetailDTO queryUserDetail(@ApiParam(required = true, name = "username", value = "用户名") @PathVariable("username") String username) {
        LOGGER.info("获取用户详情信息 username:{}", username);
        UserDetailDO userDetailDO = userService.queryUserDetail(username);
        if (userDetailDO == null) {
            return null;
        }

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        BeanUtils.copyProperties(userDetailDO, userDetailDTO);
        return userDetailDTO;
    }

    /**
     * 获取用户详情信息V2-http状态码+对象响应
     * 推荐此种响应，http反馈码是业内统一、共识的，建议在尽量不要通过解析json来获得status判断操作结果。
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/v2/users/{username}")
    @ApiOperation(value = "获取用户详情信息V2 ", httpMethod = "GET", response = UserDetailDTO.class, notes = "获取用户详情信息V2-http状态码+对象响应")
    @HystrixCommand(fallbackMethod = "queryUserDetailFallbackV2")
    public ResponseEntity<UserDetailDTO> queryUserDetailV2(@ApiParam(required = true, name = "username", value = "用户名") @PathVariable("username") String username) {
        LOGGER.info("获取用户详情信息V2 username:{}", username);
        UserDetailDO userDetailDO = userService.queryUserDetail(username);
        if (userDetailDO == null) {
            return ResponseEntity.noContent().build();
        }

        UserDetailDTO userDetailDTO = new UserDetailDTO();
        BeanUtils.copyProperties(userDetailDO, userDetailDTO);
        return ResponseEntity.ok(userDetailDTO);
    }

    /**
     * 获取用户详情Fallback
     *
     * @param username
     * @return
     */
    private UserDetailDTO queryUserDetailFallback(String username) {
        LOGGER.info("获取用户详情熔断 username:{}", username);
        UserDetailDTO userDetail = new UserDetailDTO();
        userDetail.setUsername(username);
        userDetail.setIsFallback(true);
        return userDetail;
    }

    /**
     * 获取用户详情Fallback V2
     *
     * @param username
     * @return
     */
    private ResponseEntity<UserDetailDTO> queryUserDetailFallbackV2(String username) {
        LOGGER.info("获取用户详情熔断V2 username:{}", username);
        return ResponseEntity.noContent().build();
    }
}
