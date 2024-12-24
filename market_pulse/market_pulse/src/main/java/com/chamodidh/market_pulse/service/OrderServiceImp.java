package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Order;
import com.chamodidh.market_pulse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order addOrder(CustomerDetails customerDetails, float totalAmount) {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setTotalAmount(totalAmount);
        order.setCustomerDetails(customerDetails);
       return orderRepository.save(order);
    }
}
