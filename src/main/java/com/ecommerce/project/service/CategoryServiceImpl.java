package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
   // private List<Category> categories = new ArrayList<>();



    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category) {

        categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId)
    {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND ,"Category not found with id: " + categoryId));


        categoryRepository.delete(category);
        return "category with category" + categoryId + "deleted succesfully!!";


    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
     Optional<Category> savedCategory = Optional.of(categoryRepository
             .findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found")));

     category.setCategoryId(categoryId);
     savedCategory= Optional.of(categoryRepository.save(category));
     return savedCategory.orElse(null);

    }
}