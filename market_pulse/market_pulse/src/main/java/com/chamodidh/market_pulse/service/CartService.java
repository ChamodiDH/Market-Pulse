package com.chamodidh.market_pulse.service;


import com.chamodidh.market_pulse.entity.Item;

import java.util.List;

public interface CartService {
    List<Item> addToCart(long itemId, long userId);
}
