package com.locus.ecommerce;

import com.locus.ecommerce.product.Product;
import com.locus.ecommerce.product.ProductRepository;
import com.locus.ecommerce.role.Role;
import com.locus.ecommerce.role.RoleRepository;
import com.locus.ecommerce.role.RoleService;
import com.locus.ecommerce.user.User;
import com.locus.ecommerce.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            ProductRepository productRepository,
            RoleService roleService,
            PasswordEncoder passwordEncoder
    ){
        return args -> {
            Role r1 = new Role("ROLE_CUSTOMER");
            Role r2 = new Role("ROLE_ADMIN");

            roleRepository.saveAll(
                    List.of(r1,r2)
            );

            User u1 = new User("shyam","shyamc@locus.sh","8136818533","Password123#",new ArrayList<>(),1,"Vadakara","Kozhikode","673507");
            User u2 = new User("admin","admin@locus.sh","8136818533","Password123#",new ArrayList<>(),1,"test","test","test");

            u1.setPassword(passwordEncoder.encode(u1.getPassword()));
            u2.setPassword(passwordEncoder.encode(u2.getPassword()));

            userRepository.saveAll(
                    List.of(u1,u2)
            );

            roleService.addRoleToUser(u1.getEmail(),r1.getName());
            roleService.addRoleToUser(u2.getEmail(),r2.getName());

            Product p1 =  new Product(
                    "usd23sk22i",
                    "Levis T Shirt Size(L)",
                    "Black Cotton T Shirt",
                    999,
                    599,
                    10,
                    1
            );
            Product p2 =  new Product(
                    "ler67rt234k",
                    "Wrangler Shirt Size(M)",
                    "Red Cotton Shirt",
                    2249,
                    1799,
                    5,
                    1
            );
            Product p3 =  new Product(
                    "jfs33aw93g",
                    "Lee Jeans Size(32)",
                    "Navy Blue Jeans",
                    2999,
                    1999,
                    20,
                    1
            );
            productRepository.saveAll(
                    List.of(p1,p2,p3)
            );
        };
    }
}
