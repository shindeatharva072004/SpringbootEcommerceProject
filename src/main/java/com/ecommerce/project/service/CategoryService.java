package com.ecommerce.project.service;
import com.ecommerce.project.model.Category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface CategoryService
{
    List<Category>  getAllCategories();

    void createCategory(Category category);

    String deleteCategory (Long categoryId);

    Category updateCategory(Category category, Long categoryId);


}