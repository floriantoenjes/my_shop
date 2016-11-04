package com.floriantoenjes.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }
}
