package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.entity.ProductEntity;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductWeb {
    private final ProductService productService;

    @Autowired
    public ProductWeb(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "allProducts/")
    @Operation(summary = "Get all products endpoint",
            description = "This is a products endpoint.")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "createProduct/{id}/")
    @Operation(summary = "Create product endpoint",
            description = "This is a create product endpoint.")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }
}
