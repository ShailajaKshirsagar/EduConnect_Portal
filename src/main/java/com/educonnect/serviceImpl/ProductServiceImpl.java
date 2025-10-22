package com.educonnect.serviceImpl;

import com.educonnect.entity.Product;
import com.educonnect.repository.ProductRepo;
import com.educonnect.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Override
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "Product saved";
    }
}
