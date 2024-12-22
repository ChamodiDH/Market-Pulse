package com.chamodidh.market_pulse.service;


import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.CartModel;

import java.util.List;

public interface CartService {
    List<Item> addToCart(long itemId, long userId);

    String deleteItem(long itemId, long userId);

    CartModel getCart(long userId);
}
