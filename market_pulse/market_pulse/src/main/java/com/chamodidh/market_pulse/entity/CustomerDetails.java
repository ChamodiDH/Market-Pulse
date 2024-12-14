package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CustomerDetails")
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> shippingAddress;

    @OneToOne(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private Cart cart;


    @OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();


    @OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();



}
