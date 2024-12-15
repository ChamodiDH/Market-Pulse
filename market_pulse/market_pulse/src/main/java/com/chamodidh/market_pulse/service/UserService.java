package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.model.UserModel;
import org.springframework.stereotype.Service;


public interface UserService {
    public UserModel userRegister(UserModel userModel);
}
