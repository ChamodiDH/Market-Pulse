package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.entity.Cart;
import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.CartModel;
import com.chamodidh.market_pulse.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<List<Item>> addItemToTheCart(@RequestParam long itemId, @RequestParam long userId){
       return new ResponseEntity<>(cartService.addToCart(itemId, userId), HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity< String> deleteItem(@RequestParam long itemId, @RequestParam long userId){
        return new ResponseEntity<>( cartService.deleteItem(itemId, userId), HttpStatus.OK);
    }

    @GetMapping("/getItems")
    public CartModel getCart(@RequestParam long userId){
        return cartService.getCart(userId);
    }





}
