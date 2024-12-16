package com.chamodidh.market_pulse.entity;

import com.chamodidh.market_pulse.utility.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Data
@Getter
@Table(name="Shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name = "STATUS")
    private ShipmentStatus status;

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

        Long orderId = order.getId();
        Long customerId = customerDetails.getId();

        String orderIdPart = String.format("%05d", orderId); // Order ID padded to 5 digits
        String customerIdPart = String.format("%03d", customerId); // Customer ID padded to 3 digits
        this.trackNo = orderIdPart + "-" + customerIdPart;
    }
}
