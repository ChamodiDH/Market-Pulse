package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public List<Item> addItemToTheCart(@RequestParam long itemId, @RequestParam long userId){
       return cartService.addToCart(itemId, userId);

    }
    


}
