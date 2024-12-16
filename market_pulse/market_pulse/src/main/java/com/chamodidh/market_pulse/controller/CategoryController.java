package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.model.CategoryModel;
import com.chamodidh.market_pulse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public CategoryModel addCategory(@RequestBody CategoryModel categoryModel){
        return categoryService.addCategory(categoryModel);
    }
}
