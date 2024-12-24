package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.service.PaymentService;
import com.chamodidh.market_pulse.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/charge")
    public Charge charge(@RequestParam String token, @RequestParam double amount, @RequestParam long userId) throws StripeException {
        return paymentService.managePayment(token,amount, userId);
         // Create a charge
    }
}
