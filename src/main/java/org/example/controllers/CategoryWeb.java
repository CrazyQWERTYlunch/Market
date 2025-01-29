package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.entity.CategoryEntity;
import org.example.entity.ProductEntity;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/categories/")
public class CategoryWeb {

    private final CategoryService categoryService;

    @Autowired
    public CategoryWeb(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping(value = "")
//    @Operation(summary = "Root endpoint",
//            description = "This is a root endpoint.")
//    public ResponseEntity<String> showHomePage() {
//        return new ResponseEntity<>("Hello, world!", HttpStatus.OK);
//    }

    @GetMapping(value = "allCategories/")
    @Operation(summary = "Get all categories endpoint",
            description = "This is a categories endpoint.")
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("getCategory/{id}/")
    @Operation(summary = "Get category by id endpoint",
            description = "This is a category by id endpoint.")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("updateCategory/{id}/")
    @Operation(summary = "Update category by id endpoint",
            description = "This is an update category by id endpoint.")
    public ResponseEntity<CategoryEntity> updateCategory
            (@PathVariable int id, @RequestBody CategoryEntity category) {
        CategoryEntity categoryEntity = categoryService.getCategoryById(id);
        categoryEntity.setName(category.getName());
        categoryEntity.setSlug(category.getSlug());
        categoryEntity.setImage(category.getImage());
        return new ResponseEntity<>(categoryService.updateCategory(categoryEntity), HttpStatus.OK);
    }

    @PostMapping("createCategory/{id}/")
    @Operation(summary = "Create category endpoint",
            description = "This is a create category endpoint.")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("deleteCategory/{id}/")
    @Operation(summary = "Delete category endpoint",
            description = "This is a delete category endpoint.")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        try {
            categoryService.getCategoryById(id);
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Deleted Category Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete Category Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getProductsByCategory/{categoryId}/")
    @Operation(summary = "Get products by category id endpoint",
            description = "This is an endpoint to get all products by category id.")
    public ResponseEntity<List<ProductEntity>> getProductsByCategoryId(@PathVariable int categoryId) {
        List<ProductEntity> products = categoryService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "")
    @Operation(summary = "Root endpoint", description = "This is a root endpoint.")
    public ModelAndView showCategories(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories-view");

        List<CategoryEntity> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return modelAndView;
    }
}
