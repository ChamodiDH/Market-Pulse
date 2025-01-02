package com.chamodidh.market_pulse.Exceptions.payment;

public class InvalidPaymentAmount extends RuntimeException {
    public InvalidPaymentAmount(String message) {
        super(message);
    }
}
