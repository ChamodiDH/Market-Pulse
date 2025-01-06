package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.config.CustomUserDetails;
import com.chamodidh.market_pulse.exceptions.user.UserAlreadyExists;
import com.chamodidh.market_pulse.entity.Cart;
import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.SupplierDetails;
import com.chamodidh.market_pulse.entity.User;
import com.chamodidh.market_pulse.exceptions.user.UserDoesNotExistsException;
import com.chamodidh.market_pulse.model.UserModel;
import com.chamodidh.market_pulse.repository.CustomerDetailsRepository;
import com.chamodidh.market_pulse.repository.SupplierDetailsRepository;
import com.chamodidh.market_pulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SupplierDetailsRepository supplierDetailsRepository;
    @Autowired
    CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    BCryptPasswordEncoder bycryptPasswordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Override
    public UserModel userRegister(UserModel userModel) {
        try {
            User user = new User();

            if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
                throw new UserAlreadyExists("User with" + userModel.getEmail() + "email address already exists");
            }


            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            user.setEmail(userModel.getEmail());
            user.setPassword(bycryptPasswordEncoder.encode(userModel.getPassword()));
            user.setContact(userModel.getContact());
            user.setRoles(userModel.getRoles());
            user.setRegisteredDate(new Date());

            User savedUser = userRepository.save(user);

            if (user.getRoles().contains("SUPPLIER")) {
                SupplierDetails supplier = new SupplierDetails();
                supplier.setUser(user);
                supplier.setCompanyAddress(userModel.getCompanyAddress());
                supplierDetailsRepository.save(supplier);
            }
            if (user.getRoles().contains("CUSTOMER")) {
                CustomerDetails customer = new CustomerDetails();
                Cart cart = new Cart();
                customer.setUser(user);
                customer.setShippingAddress(userModel.getShippingAddress());
                cart.setCustomerDetails(customer);
                customer.setCart(cart);
                customerDetailsRepository.save(customer);
            }

            return UserModel.builder().firstName(savedUser.getFirstName()).
                    lastName(savedUser.getLastName()).email(savedUser.getEmail()).password(savedUser.getPassword()).contact(savedUser.getContact()).roles(savedUser.getRoles()).registeredDate(savedUser.getRegisteredDate()).build();
        } catch (UserAlreadyExists e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during user registration", e);
        }


    }

    @Override
    public String userLogin(UserModel userModel) {
        User user = userRepository.findByEmail(
                userModel.getEmail()).orElseThrow(() -> new UserDoesNotExistsException("User not found"));
        if(!Objects.isNull(user)){
            if(user.getPassword().equals(userModel.getPassword())) {
                return "Login Successful";
            }else{
                return "Login Failed due to incorrect password";
            }
        }else {
         return "Incorrect Email";
        }

    }

    @Override
    public String verifyUser(UserModel userModel) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userModel.getEmail(), userModel.getPassword()
        ));
//        User user = userRepository.findByEmail(
//                userModel.getEmail()).orElseThrow(() -> new UserDoesNotExistsException("User not found"));
        if(authenticate.isAuthenticated()){

            return jwtService.generateToken(userModel);

               // return "token";

        }else {
            return "Incorrect Email";
        }

    }

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
