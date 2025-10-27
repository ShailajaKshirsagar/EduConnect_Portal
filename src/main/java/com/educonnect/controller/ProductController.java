package com.educonnect.controller;

import com.educonnect.entity.Product;
import com.educonnect.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product_management",description = "API's for product management")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add new product",description = "Adds new product")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        String msg = productService.saveProduct(product);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/searchProduct")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Search product",description = "Finds product by keyword")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
       List<Product> productByKey = productService.getProductByKey(keyword);
       return new ResponseEntity<>(productByKey, HttpStatus.OK);
    }
}
