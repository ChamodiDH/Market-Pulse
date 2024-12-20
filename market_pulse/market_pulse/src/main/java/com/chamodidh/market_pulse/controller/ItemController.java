package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.ItemModel;
import com.chamodidh.market_pulse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    //add item to the system by seller
    @PostMapping("/add")
    public Item addItemsToTheSystem(@RequestBody ItemModel itemModel){
        return itemService.addItem(itemModel);
    }

    //delete item from the system by the seller
    @DeleteMapping("/delete/{id}")
    public String removeItem(@PathVariable long id){
        return itemService.removeItem(id);
    }
    //update item details by the seller

    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable long id, @RequestBody ItemModel itemModel){
        return itemService.updateItem(id,itemModel);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id){
        return itemService.getItemById(id);
    }

    @GetMapping("/items")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @GetMapping("/items/search")
    public List<Item> searchItems(@RequestParam String keyword){
        return itemService.searchItems(keyword);

    }




}
