package com.chamodidh.market_pulse.controller;

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

    @PostMapping("/charge")
    public Charge charge(@RequestParam String token, @RequestParam double amount) throws StripeException {
        return stripeService.createCharge(token, amount); // Create a charge
    }
}
