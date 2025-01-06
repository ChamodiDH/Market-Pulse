package com.chamodidh.market_pulse.exceptions.user;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
