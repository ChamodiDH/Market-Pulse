package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UNIT_PRICE")
    private float unitPrice;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "BRAND_NAME")
    private String brandName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private SupplierDetails supplierDetails;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "items")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    //one to many review


}
