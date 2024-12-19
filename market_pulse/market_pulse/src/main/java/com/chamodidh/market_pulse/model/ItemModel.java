package com.chamodidh.market_pulse.model;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ItemModel {

    private float unitPrice;
    private String itemName;
    private String brandName;
    private String description;
    private float quantity;
    private long supplierId;
    private long categoryId;

}
