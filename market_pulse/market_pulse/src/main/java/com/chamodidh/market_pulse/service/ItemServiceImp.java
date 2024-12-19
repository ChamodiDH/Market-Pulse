package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Category;
import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.entity.SupplierDetails;
import com.chamodidh.market_pulse.model.ItemModel;
import com.chamodidh.market_pulse.repository.CategoryRepository;
import com.chamodidh.market_pulse.repository.ItemServiceRepository;
import com.chamodidh.market_pulse.repository.SupplierDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImp implements ItemService{
    @Autowired
    ItemServiceRepository itemServiceRepository;
    @Autowired
    SupplierDetailsRepository supplierDetailsRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Item addItem(ItemModel itemModel) {
        SupplierDetails supplierDetails = supplierDetailsRepository.findById(itemModel.getSupplierId()).get();
        Category category = categoryRepository.findById(itemModel.getCategoryId()).get();
        Item item = new Item();
        item.setItemName(item.getItemName());
        item.setCategory(category);
        item.setDescription(item.getDescription());
        item.setQuantity(item.getQuantity());
        item.setBrandName(item.getBrandName());
        item.setDescription(itemModel.getDescription());
        item.setSupplierDetails(supplierDetails);

      return itemServiceRepository.save(item);


    }

    @Override
    public String removeItem(long id) {
        try{
            itemServiceRepository.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Item updateItem(long id, ItemModel itemModel) {
      Item item = itemServiceRepository.findById(id).get();
        SupplierDetails supplierDetails = supplierDetailsRepository.findById(itemModel.getSupplierId()).get();
        Category category = categoryRepository.findById(itemModel.getCategoryId()).get();
        item.setItemName(itemModel.getItemName());
        item.setCategory(category);
        item.setDescription(itemModel.getDescription());
        item.setQuantity(itemModel.getQuantity());
        item.setBrandName(itemModel.getBrandName());
        item.setDescription(itemModel.getDescription());
        item.setSupplierDetails(supplierDetails);

        return itemServiceRepository.save(item);


    }

    @Override
    public Item getItemById(long id) {
      return itemServiceRepository.findById(id).get();
    }

    @Override
    public List<Item> getItems() {
        return itemServiceRepository.findAll();
    }
}
