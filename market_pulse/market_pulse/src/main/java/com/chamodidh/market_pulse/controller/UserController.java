package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.model.UserModel;
import com.chamodidh.market_pulse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserModel userRegister(@RequestBody UserModel userModel){
       return userService.userRegister(userModel);
    }


}
