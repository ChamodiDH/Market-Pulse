package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.config.CustomUserDetails;
import com.chamodidh.market_pulse.entity.User;
import com.chamodidh.market_pulse.exceptions.user.UserDoesNotExistsException;
import com.chamodidh.market_pulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = userRepository.findByEmail(
                    username).orElseThrow(() -> new UserDoesNotExistsException("User not found"));
            if(Objects.isNull(user)){
                System.out.println("User not available");
                throw new UsernameNotFoundException("User not found");
            }else {
                return new CustomUserDetails(user);
            }

        }
    }

