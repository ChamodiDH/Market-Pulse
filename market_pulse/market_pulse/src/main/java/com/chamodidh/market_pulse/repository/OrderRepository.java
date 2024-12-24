package com.chamodidh.market_pulse.repository;

import com.chamodidh.market_pulse.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrderRepository extends JpaRepository<Order, Long> {

}
