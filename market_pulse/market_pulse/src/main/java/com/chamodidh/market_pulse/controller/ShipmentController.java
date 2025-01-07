package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.entity.Shipment;
import com.chamodidh.market_pulse.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    @GetMapping("/details/{id}")
    public ResponseEntity<Shipment> getShipmentDetails(@PathVariable long id){
        return new ResponseEntity<>(shipmentService.getShipmentDetails(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<Shipment> updateShipmentStatus(@PathVariable long id,@RequestParam String status){
        return new ResponseEntity<>(shipmentService.updateShipmentStatus(id, status), HttpStatus.OK);
    }
}
