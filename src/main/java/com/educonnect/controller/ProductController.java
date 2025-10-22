package com.educonnect.controller;

import com.educonnect.entity.Product;
import com.educonnect.service.ProductService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        String msg = productService.saveProduct(product);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/searchProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
       List<Product> productByKey = productService.getProductByKey(keyword);
       return new ResponseEntity<>(productByKey, HttpStatus.OK);
    }
}
