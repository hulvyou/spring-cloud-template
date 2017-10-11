package com.spring.cloud.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author Travel Hu
 */
@Controller
public class UserController {

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/pageInfo")
    @ResponseBody
    public void pageInfo() {
        System.out.println("=================");
    }
}
