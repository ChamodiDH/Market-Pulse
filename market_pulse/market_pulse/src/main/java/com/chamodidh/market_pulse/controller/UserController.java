package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.model.UserModel;
import com.chamodidh.market_pulse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserModel> userRegister(@RequestBody UserModel userModel){

        return new ResponseEntity<>(userService.userRegister(userModel), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserModel userModel){

        return new ResponseEntity<>(userService.verifyUser(userModel), HttpStatus.OK);
    }


}
