package com.spring.cloud.oauth2.service;

import com.spring.cloud.oauth2.domain.UserDetailDO;
import com.spring.cloud.user.api.UserService;
import com.spring.cloud.user.dto.UserDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 定制用户登录
 *
 * @author Travel Hu
 */
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailDTO userDetailDTO = userService.findUserByUsername(username);
        if (userDetailDTO == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserDetailDO userDetailDO = new UserDetailDO();
        userDetailDO.setId(userDetailDTO.getUserId());
        userDetailDO.setUsername(userDetailDTO.getUsername());
        userDetailDO.setPassword(userDetailDTO.getSecret());
        userDetailDO.setRoleList(userDetailDTO.getRoleList());
        return userDetailDO;
    }
}
