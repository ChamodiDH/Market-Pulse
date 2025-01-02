package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Review")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    //one to many user check
    //one to many item check

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="RATING")
    private int rate;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerDetails customerDetails;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;


}
