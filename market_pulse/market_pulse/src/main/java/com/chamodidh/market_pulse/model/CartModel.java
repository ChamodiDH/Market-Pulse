package com.chamodidh.market_pulse.model;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class CartModel {

    private Long id;
    private int numberOfItems;
    private float totalCost;
    private List<Item> items;

}
