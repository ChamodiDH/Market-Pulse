package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="Shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ESTIMATED_DELIVERY_DATE")
    @Temporal(TemporalType.DATE)
    private Date estimatedDate;

    @Column(name = "TRACK_NO", unique = true, nullable = false, length = 20)
    private String trackNo;

    @OneToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerDetails customerDetails;

    @PrePersist
    private void generateTrackNo() {
       
        if (order != null && customerDetails != null) {
            String orderIdPart = String.format("%05d", order.getId()); // Order ID padded to 5 digits
            String customerIdPart = String.format("%03d", customerDetails.getId()); // Customer ID padded to 3 digits
            this.trackNo = orderIdPart + "-" + customerIdPart;
        } else {
            throw new IllegalStateException("Order and CustomerDetails must be set before saving Shipment");
        }
    }
}
