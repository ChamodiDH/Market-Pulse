package com.chamodidh.market_pulse.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    @Value("${stripe.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey; // Initialize Stripe with secret key
    }

    public Charge createCharge(String token, double amount) throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (amount * 100)) // Convert to cents
                .setCurrency("usd")
                .setDescription("Charge for order")
                .setSource(token) // The token generated on the frontend
                .build();

        return Charge.create(params); // Perform the charge
    }
}
