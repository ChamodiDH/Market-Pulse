package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Cart;
import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.repository.CartRepository;
import com.chamodidh.market_pulse.repository.ItemServiceRepository;
import com.chamodidh.market_pulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
