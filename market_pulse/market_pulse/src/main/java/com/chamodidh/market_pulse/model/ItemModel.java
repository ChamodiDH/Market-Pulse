package com.chamodidh.market_pulse.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {

    private float unitPrice;
    private String itemName;
    private String brandName;
    private String description;
    private float quantity;
    private long supplierId;
    private long categoryId;

}
