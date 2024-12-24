package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Order;
import com.chamodidh.market_pulse.entity.Shipment;
import com.chamodidh.market_pulse.utility.enums.ShipmentStatus;

import java.util.Date;

public interface ShipmentService {
    public Shipment addShipment(ShipmentStatus status, Date estimateddate, Order order, CustomerDetails customerDetail);

    Shipment getShipmentDetails(long id);

    Shipment updateShipmentStatus(long id, String status);
}
