package com.chamodidh.market_pulse.repository;

import com.chamodidh.market_pulse.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
