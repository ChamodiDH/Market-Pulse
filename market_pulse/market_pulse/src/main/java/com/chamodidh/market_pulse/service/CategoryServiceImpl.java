package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.Category;
import com.chamodidh.market_pulse.model.CategoryModel;
import com.chamodidh.market_pulse.repository.CategoryRepository;
import com.chamodidh.market_pulse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public CategoryModel addCategory(CategoryModel categoryModel) {

        Category category = Category.builder().type(categoryModel.getType()).build();
        Category savedCategory = categoryRepository.save(category);

        return CategoryModel.builder().type(savedCategory.getType()).build();


    }
}
