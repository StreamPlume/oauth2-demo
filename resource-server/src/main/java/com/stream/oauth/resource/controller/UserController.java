package com.stream.oauth.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Stream on 2018/11/7.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }
}
