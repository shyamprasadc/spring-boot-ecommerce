package com.locus.ecommerce.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductSeeder {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            Product p1 =  new Product(
                    "usd23sk22i",
                    "Levis T Shirt Size(L)",
                    "Black Cotton T Shirt",
                    999,
                    599,
                    10,
                    1,
                    null,
                    null
            );
            Product p2 =  new Product(
                    "ler67rt234k",
                    "Wrangler Shirt Size(M)",
                    "Red Cotton Shirt",
                    2249,
                    1799,
                    5,
                    1,
                    null,
                    null
            );
            Product p3 =  new Product(
                    "jfs33aw93g",
                    "Lee Jeans Size(32)",
                    "Navy Blue Jeans",
                    2999,
                    1999,
                    20,
                    1,
                    null,
                    null
            );
            productRepository.saveAll(
                    List.of(p1,p2,p3)
            );
        };
    }
}
