package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "SupplierDetails")
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDetails {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="SUPPLIER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;

    @OneToMany(mappedBy = "supplierDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}
