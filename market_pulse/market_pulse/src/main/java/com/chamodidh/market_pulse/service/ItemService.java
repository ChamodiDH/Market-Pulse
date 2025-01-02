package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.ItemModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ItemService {
    Item addItem(ItemModel itemModel);

    String removeItem(long id);

    Item updateItem(long id, ItemModel itemModel);

    Item getItemById(long id);

    List<Item> getItems(Pageable pageable);

    List<Item> searchItems(String keyword);
}
