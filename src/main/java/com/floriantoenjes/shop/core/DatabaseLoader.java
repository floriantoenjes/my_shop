package com.floriantoenjes.shop.core;


import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductRepository;
import com.floriantoenjes.shop.user.Role;
import com.floriantoenjes.shop.user.User;
import com.floriantoenjes.shop.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("password");
        user1.setRole(new Role("ROLE_USER"));
        userRepository.save(user1);

        Product fork = new Product("Fork", 5.0, 3L);
        Product spoon = new Product("Spoon", 6.99, 10L);
        Product knife = new Product("Knife", 3.49, 15L);

        productRepository.save(fork);
        productRepository.save(spoon);
        productRepository.save(knife);
    }
}
