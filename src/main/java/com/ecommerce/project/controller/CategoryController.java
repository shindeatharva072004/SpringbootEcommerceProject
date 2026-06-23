package com.ecommerce.project.controller;
import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class CategoryController
{

    @Autowired
    private CategoryService categoryService;


    @GetMapping("public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories
            (@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
             @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
             @RequestParam (name =  "sortBy" , defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
             @RequestParam (name =  "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

        CategoryResponse categoryResponse =
                categoryService.getAllCategories(pageNumber, pageSize , sortBy , sortOrder );
    Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
: Sort.by(sortBy).descending();

        return new ResponseEntity<>(categoryResponse, OK);
    }

    @PostMapping("public/categories")
    //@RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO , HttpStatus.CREATED);
    }

    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {

        String status = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }



    @PutMapping("public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO , @PathVariable Long categoryId)     {

            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO , categoryId);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);


    }
}