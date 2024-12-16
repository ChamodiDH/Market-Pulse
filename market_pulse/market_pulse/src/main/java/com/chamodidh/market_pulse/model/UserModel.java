package com.chamodidh.market_pulse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> roles;
    private List<String> contact;
    private List<String> shippingAddress;
    private String companyAddress;
    private Date registeredDate;
}
