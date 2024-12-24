package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Order;
import com.chamodidh.market_pulse.entity.Shipment;
import com.chamodidh.market_pulse.repository.ShipmentRepository;
import com.chamodidh.market_pulse.utility.enums.ShipmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShipmentServiceImpl implements ShipmentService{
    @Autowired
    ShipmentRepository shipmentRepository;
    @Override
    public Shipment addShipment(ShipmentStatus status, Date estimateddate, Order order, CustomerDetails customerDetail) {
        Shipment shipment = new Shipment();
        shipment.setStatus(status);
        shipment.setOrder(order);
        shipment.setCustomerDetails(customerDetail);
        shipment.setEstimatedDate(estimateddate);
      return shipmentRepository.save(shipment);

    }

    @Override
    public Shipment getShipmentDetails(long id) {
        return shipmentRepository.findById(id).get();
    }

    @Override
    public Shipment updateShipmentStatus(long id, String status) {
       Shipment shipment = shipmentRepository.findById(id).get();
        if (status != null) {
            try {
                ShipmentStatus shipmentStatus = ShipmentStatus.valueOf(status.toUpperCase());
                shipment.setStatus(shipmentStatus);
            return shipmentRepository.save(shipment);
            } catch (IllegalArgumentException e) {
                // If the string doesn't match any enum, this block executes
                System.out.println("Invalid ShipmentStatus: " + status);
            }
        }
    }
}
