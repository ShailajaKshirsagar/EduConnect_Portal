package com.educonnect.service;

import com.educonnect.entity.Product;

import java.util.List;

public interface ProductService {
    String saveProduct(Product product);

    List<Product> getProductByKey(String keyword);
}
