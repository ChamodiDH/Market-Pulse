package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.entity.Shipment;
import com.chamodidh.market_pulse.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    @GetMapping("/details/{id}")
    public Shipment getShipmentDetails(@PathVariable long id){
        return shipmentService.getShipmentDetails(id);
    }

    @PutMapping("/update/{id}")
    public Shipment updateShipmentStatus(@PathVariable long id,@RequestParam String status){
        return  shipmentService.updateShipmentStatus(id, status);
    }
}
