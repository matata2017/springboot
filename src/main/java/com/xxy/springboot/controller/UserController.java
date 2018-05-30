package com.xxy.springboot.controller;

import com.xxy.springboot.system.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shinelon on 2018/4/18.
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {
    @PostMapping("")
    public void loginUser(@RequestBody User user){
        System.out.print("user");
    }
}
