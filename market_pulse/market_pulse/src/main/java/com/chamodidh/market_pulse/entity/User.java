package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL_ID")
    private String email;

    @Column(name="PASSWORD")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> contact;

    @Column(name = "REGISTERED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerDetails customerDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SupplierDetails supplierDetails;



}
