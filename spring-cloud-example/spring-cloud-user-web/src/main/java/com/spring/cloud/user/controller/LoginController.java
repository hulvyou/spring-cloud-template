package com.spring.cloud.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * @author Travel Hu
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    @ResponseBody
    public Principal home(Principal user) {
        return user;
    }

    @GetMapping("/info1")
    @ResponseBody
    @PreAuthorize("hasAuthority('A01')")
    public String info1(){
        return "info1";
    }

    @GetMapping("/info2")
    @ResponseBody
    @PreAuthorize("hasAuthority('A0101')")
    public String info2(){
        return "info2";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        Cookie[] cookies = request.getCookies();
        for(int i = 0,len = cookies.length; i < len; i++) {
            Cookie cookie = new Cookie(cookies[i].getName(),null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/";
    }

}
