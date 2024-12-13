package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CustomerDetails")
public class SupplierDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOMER_ID")
    private String id;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;
}
