package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

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

    @ManyToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();

    //one to many review


}
