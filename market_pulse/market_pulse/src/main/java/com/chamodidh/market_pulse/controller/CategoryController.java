package com.chamodidh.market_pulse.controller;

import com.chamodidh.market_pulse.model.CategoryModel;
import com.chamodidh.market_pulse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<CategoryModel> addCategory(@RequestBody CategoryModel categoryModel){
        return new ResponseEntity<>(categoryService.addCategory(categoryModel), HttpStatus.CREATED);
    }
}
