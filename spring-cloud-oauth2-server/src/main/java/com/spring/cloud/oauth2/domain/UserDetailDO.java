package com.spring.cloud.oauth2.domain;

import com.spring.cloud.user.dto.AuthorityDTO;
import com.spring.cloud.user.dto.RoleDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Travel Hu
 */
public class UserDetailDO implements UserDetails {
    private Long id;
    private String username;
    private String password;

    private List<RoleDTO> roleList;

    @Override
    public Collection<? extends DetailGrantedAuthority> getAuthorities() {
        List<DetailGrantedAuthority> authors = new ArrayList<>();
        List<RoleDTO> roles = this.getRoleList();
        for (RoleDTO role : roles) {
            for (AuthorityDTO authorityDTO :role.getAuthorityList()){
                authors.add(new DetailGrantedAuthority(authorityDTO.getCode()));
            }
        }
        return authors;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserDetailDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
