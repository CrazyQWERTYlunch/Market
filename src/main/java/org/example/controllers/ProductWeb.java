package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.entity.CategoryEntity;
import org.example.entity.ProductEntity;
import org.example.services.CategoryService;
import org.example.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductWeb {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductWeb(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/allProducts")
    @Operation(summary = "Get all products endpoint",
            description = "This is a products endpoint.")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/createProduct/{id}")
    @Operation(summary = "Create product endpoint",
            description = "This is a create product endpoint.")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/createProduct")
    public ModelAndView addProductForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-product");

        model.addAttribute("product", new ProductEntity());
        model.addAttribute("categories", categoryService.getAllCategories());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView addProduct(@Valid @ModelAttribute ProductEntity product, BindingResult bindingResult, @RequestParam int categoryId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("add-product");
        }
        Logger logger = LoggerFactory.getLogger(ProductWeb.class);
        logger.info("Получен продукт: {}", product);
        logger.info("ID категории: {}", categoryId);

        CategoryEntity category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);
        product.setUpdateAt(LocalDateTime.now());

        ProductEntity savedProduct = productService.createProduct(product);
        logger.info("Сохраненный продукт: {}", savedProduct);
        return new ModelAndView("redirect:/catalog");
    }

    @GetMapping("/{id}")
    public ModelAndView getProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return new ModelAndView("product");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return new ModelAndView("edit-product");
    }

    @PatchMapping("/{id}")
    public ModelAndView updateProduct(@ModelAttribute("product") @Valid ProductEntity productUpdates, @PathVariable("id") int id) {
        ProductEntity product = productService.getProductById(id);

        // Частичное обновление полей
        if (productUpdates.getName() != null) {
            product.setName(productUpdates.getName());
        }
        if (productUpdates.getSlug() != null) {
            product.setSlug(productUpdates.getSlug());
        }

        product.setPrice(productUpdates.getPrice());
        product.setQuantity(productUpdates.getQuantity());
        if (productUpdates.getImage() != null) {
            product.setImage(productUpdates.getImage());
        }
        if (productUpdates.getCategory() != null) {
            CategoryEntity category = categoryService.getCategoryById(productUpdates.getCategory().getId());
            product.setCategory(category);
        }

        productService.updateProduct(product);

        return new ModelAndView("redirect:/products/" + id);
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return new ModelAndView("redirect:/catalog");
    }
}
