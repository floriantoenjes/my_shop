package com.floriantoenjes.shop.core;


import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner{

    private final ProductRepository productRepository;

    @Autowired
    public DatabaseLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Product fork = new Product("Fork", 5.0);
        Product spoon = new Product("Spoon", 6.99);
        Product knife = new Product("Knife", 3.49);

        productRepository.save(fork);
        productRepository.save(spoon);
        productRepository.save(knife);
    }
}
