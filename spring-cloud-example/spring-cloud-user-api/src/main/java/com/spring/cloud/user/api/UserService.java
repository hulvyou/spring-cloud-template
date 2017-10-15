package com.spring.cloud.user.api;

import com.spring.cloud.user.dto.UserDetailDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户api v1
 *
 * @author Travel Hu
 */
@FeignClient(name = "spring-cloud-user-server",url = "http://localhost:8080")
@RequestMapping("/api")
public interface UserService {

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/v1/users/{username}")
    UserDetailDTO findUserByUsername(@PathVariable("username") String username);
}
