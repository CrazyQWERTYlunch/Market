package org.example.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.example.entity.ProductEntity;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogWeb {
    ProductService productService;

    @Autowired
    public CatalogWeb(ProductService productService) {this.productService = productService;}

    @GetMapping("")
    @Operation(summary = "Get all catalog endpoint",
            description = "This is a catalog endpoint.")
    public ModelAndView showCatalog(Model model) {
        ModelAndView modelAndView = new ModelAndView("catalog");
        List<ProductEntity> products = productService.getAllProducts();
        modelAndView.addObject("products", products);

        return modelAndView;
    }
}
