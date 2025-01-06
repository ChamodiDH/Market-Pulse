package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.exceptions.category.CategoryNotFoundException;
import com.chamodidh.market_pulse.exceptions.item.*;
import com.chamodidh.market_pulse.entity.Category;
import com.chamodidh.market_pulse.entity.Item;
import com.chamodidh.market_pulse.entity.SupplierDetails;
import com.chamodidh.market_pulse.model.ItemModel;
import com.chamodidh.market_pulse.repository.CategoryRepository;
import com.chamodidh.market_pulse.repository.ItemServiceRepository;
import com.chamodidh.market_pulse.repository.SupplierDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
        item.setItemName(itemModel.getItemName());
        item.setCategory(category);
        item.setQuantity(itemModel.getQuantity());
        item.setBrandName(itemModel.getBrandName());
        item.setDescription(itemModel.getDescription());
        item.setSupplierDetails(supplierDetails);
        item.setUnitPrice(itemModel.getUnitPrice());

      return itemServiceRepository.save(item);


    }

    @Override
    public String removeItem(long id) {
        if(itemServiceRepository.findById(id).isEmpty()){
            throw  new ItemNotFoundException("Item not found");
        }

        try{
            itemServiceRepository.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Item updateItem(long id, ItemModel itemModel) {

      Item item = itemServiceRepository.findById(id).orElseThrow(
                ()-> new ItemNotFoundException("Item not found")
      );
        SupplierDetails supplierDetails = supplierDetailsRepository.findById(itemModel.getSupplierId()).orElseThrow(
                ()-> new SupplierNotFound("Supplier not found")
        );
        Category category = categoryRepository.findById(itemModel.getCategoryId()).orElseThrow(
                ()-> new CategoryNotFoundException("Category not found")
        );

        if (itemModel.getItemName() != null && !itemModel.getItemName().isEmpty()) {
            item.setItemName(itemModel.getItemName());
        }else {
            throw new InvalidItemDetailException("Item name cannot be empty");
        }

        if (itemModel.getDescription() != null && !itemModel.getDescription().isEmpty()) {
            item.setDescription(itemModel.getDescription());
        }else{
            throw new InvalidItemDetailException("Description cannot be empty");
        }

        if (itemModel.getQuantity() > 0) {
            item.setQuantity(itemModel.getQuantity());
        }else{
            throw new InvalidItemDetailException("Quantity cannot be 0 or negative");
        }

        if (itemModel.getBrandName() != null && !itemModel.getBrandName().isEmpty()) {
            item.setBrandName(itemModel.getBrandName());
        }else{
            throw new InvalidItemDetailException("Brand name cannot be empty");
        }

        if (itemModel.getUnitPrice() > 0) {
            item.setUnitPrice(itemModel.getUnitPrice());
        }else {
            throw new InvalidItemDetailException("Unit price cannot be 0 or negative");
        }

        item.setSupplierDetails(supplierDetails);
        item.setCategory(category);

        return itemServiceRepository.save(item);


    }

    @Override
    public Item getItemById(long id) {

        return itemServiceRepository.findById(id).orElseThrow(
                ()-> new ItemNotFoundException("Item not found")
        );
    }

    @Override
    public List<Item> getItems(Pageable pageable) {

        if (pageable == null || pageable.getPageNumber() < 0 || pageable.getPageSize() <= 0) {
            throw new InvalidPaginationParameterException("Pageable parameters are invalid. Page number and size must be positive.");
        }

        return itemServiceRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Item> searchItems(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new InvalidSearchKeywordException("Search keyword cannot be null or empty.");
        }

        return itemServiceRepository.searchItemsByKeyword(keyword);
    }
}
