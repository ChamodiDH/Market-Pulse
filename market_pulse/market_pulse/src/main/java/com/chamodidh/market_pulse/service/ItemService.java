package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.model.ItemModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    Item addItem(ItemModel itemModel);

    String removeItem(long id);

    Item updateItem(long id, ItemModel itemModel);

    Item getItemById(long id);

    List<Item> getItems();
}
