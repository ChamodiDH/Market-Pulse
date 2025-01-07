package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.ItemModel;
import com.chamodidh.market_pulse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    //add item to the system by seller
    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<Item> addItemsToTheSystem(@RequestBody ItemModel itemModel){

        return new ResponseEntity<>(itemService.addItem(itemModel), HttpStatus.CREATED);
    }

    //delete item from the system by the seller
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public  ResponseEntity<String> removeItem(@PathVariable long id){
        return  new ResponseEntity<>(itemService.removeItem(id), HttpStatus.OK);
    }
    //update item details by the seller

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('SUPPLIER')")
    public  ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody ItemModel itemModel){
       return  new ResponseEntity<>(itemService.updateItem(id,itemModel),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Item> getItem(@PathVariable long id){
        return  new ResponseEntity<>(itemService.getItemById(id),HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(itemService.getItems(PageRequest.of(page,size)), HttpStatus.OK);
    }

    @GetMapping("/items/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String keyword){
        return new ResponseEntity<>(itemService.searchItems(keyword), HttpStatus.OK);

    }


}
