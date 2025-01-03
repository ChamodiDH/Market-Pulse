package com.chamodidh.market_pulse.exceptions.payment;

public class InvalidPaymentAmount extends RuntimeException {
    public InvalidPaymentAmount(String message) {
        super(message);
    }
}
