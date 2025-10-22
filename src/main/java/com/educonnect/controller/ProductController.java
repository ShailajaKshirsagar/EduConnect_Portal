package com.educonnect.controller;

import com.educonnect.entity.Product;
import com.educonnect.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN','FACULTY')")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        String msg = productService.saveProduct(product);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

}
