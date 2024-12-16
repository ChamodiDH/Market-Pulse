package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name="Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="NO_OF_ITEMS")
    private int numberOfItems;

    @Column(name="TOTAL_COST")
    private float totalCost;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerDetails customerDetails;

    @ManyToMany
    @JoinTable(
            name = "cart_item", // Join table name
            joinColumns = @JoinColumn(name = "cart_id"), // Foreign key to Cart
            inverseJoinColumns = @JoinColumn(name = "item_id") // Foreign key to Item
    )
    private List<Item> items = new ArrayList<>();

}
