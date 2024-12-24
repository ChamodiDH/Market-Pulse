package com.chamodidh.market_pulse.repository;

import com.chamodidh.market_pulse.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
