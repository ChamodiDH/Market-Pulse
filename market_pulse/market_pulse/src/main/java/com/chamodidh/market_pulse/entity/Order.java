package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="ID")
    private Long id;


    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "TOTAL_AMOUNT")
    private float totalAmount;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerDetails customerDetails;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Shipment shipment;

}
