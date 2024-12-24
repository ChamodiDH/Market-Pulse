package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Order;
import com.chamodidh.market_pulse.entity.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public interface PaymentService {
    Charge managePayment(String token, double amount, long userId) throws StripeException;
    Payment addPayment(Order order, CustomerDetails customerDetails, float amount);
}
