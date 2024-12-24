package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Cart;
import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.CartModel;
import com.chamodidh.market_pulse.repository.CartRepository;
import com.chamodidh.market_pulse.repository.ItemServiceRepository;
import com.chamodidh.market_pulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepository cartRepository;
    ItemServiceRepository itemServiceRepository;
    UserRepository userRepository;
    @Override
    public List<Item> addToCart(long itemId, long userId) {
        //get the cart of the user
       Cart cart = userRepository.findById(userId).get().getCustomerDetails().getCart();
        //get the item
        Item item = itemServiceRepository.findById(itemId).get();
        //save the cart to the item
        cart.getItems().add(item);
        cart.setTotalCost(cart.getTotalCost() + item.getUnitPrice());

        cartRepository.save(cart);
        return cartRepository.findById(cart.getId()).get().getItems();

    }

    @Override
    public String deleteItem(long itemId, long userId) {
        Cart cart = userRepository.findById(userId).get().getCustomerDetails().getCart();
        Item item = itemServiceRepository.findById(itemId).get();
        cart.getItems().remove(item);
        cart.setTotalCost(cart.getTotalCost() - item.getUnitPrice());
        cartRepository.save(cart);
        return "Removed successfully";

    }

    @Override
    public CartModel getCart(long userId) {
        Cart cart = userRepository.findById(userId).get().getCustomerDetails().getCart();
        return CartModel.builder().id(cart.getId()).totalCost(cart.getTotalCost()).items(cart.getItems()).numberOfItems(cart.getNumberOfItems()).build();
    }

    @Override
    public void emptyCart(long userId) {
        Cart cart = userRepository.findById(userId).get().getCustomerDetails().getCart();
        cart.setItems(new ArrayList<>());
        cart.setTotalCost(0.0f);
        cartRepository.save(cart);
        System.out.println("Cart emptied");
    }


}
