package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Order;

public interface OrderService {
    public Order addOrder(CustomerDetails customerDetails, float totalAmount);
}
