package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
        return ;
    }

    @Override
    public String deleteCategory(Long categoryId)
    {
        Category category = categories.stream()
                .filter(c ->c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND , "Reason Not FOund"));
        if(category==null)

            return"category not found";

        categories.remove(category);
        return "category with category" + categoryId + "deleted succesfully!!";


    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
        Optional<Category>  optionalcategory = categories.stream()
                .filter(c ->c.getCategoryId().equals(categoryId))
                .findFirst();


        if(optionalcategory.isPresent())
        {
            Category existingCategory = optionalcategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }
            else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"category not found");
        }
    }
}