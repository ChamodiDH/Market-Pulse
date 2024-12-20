package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Cart;
import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.SupplierDetails;
import com.chamodidh.market_pulse.entity.User;
import com.chamodidh.market_pulse.model.UserModel;
import com.chamodidh.market_pulse.repository.CustomerDetailsRepository;
import com.chamodidh.market_pulse.repository.SupplierDetailsRepository;
import com.chamodidh.market_pulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    SupplierDetailsRepository supplierDetailsRepository;
    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Override
    public UserModel userRegister(UserModel userModel) {
       User user = new User();

       user.setFirstName(userModel.getFirstName());
       user.setLastName(userModel.getLastName());
       user.setEmail(userModel.getEmail());
       user.setPassword(userModel.getPassword());
       user.setContact(userModel.getContact());
       user.setRoles(userModel.getRoles());
       user.setRegisteredDate(new Date());

     User savedUser =  userRepository.save(user);

     if(user.getRoles().contains("SUPPLIER")){
         SupplierDetails supplier = new SupplierDetails();
         supplier.setUser(user);
         supplier.setCompanyAddress(userModel.getCompanyAddress());
         supplierDetailsRepository.save(supplier);
     }
     if(user.getRoles().contains("CUSTOMER")){
            CustomerDetails customer = new CustomerDetails();
            Cart cart = new Cart();
            customer.setUser(user);
            customer.setShippingAddress(userModel.getShippingAddress());
            customer.setCart(cart);
            customerDetailsRepository.save(customer);
        }

     return  UserModel.builder().firstName(savedUser.getFirstName()).
             lastName(savedUser.getLastName()).email(savedUser.getEmail()).password(savedUser.getPassword()).contact(savedUser.getContact()).roles(savedUser.getRoles()).registeredDate(savedUser.getRegisteredDate()).build();


    }
}
