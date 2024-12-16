package com.chamodidh.market_pulse.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name="Category")
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();


}

