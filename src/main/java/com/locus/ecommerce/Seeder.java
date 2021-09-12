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
        CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository,
                        ProductRepository productRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
                return args -> {
                        Role r1 = new Role("ROLE_CUSTOMER");
                        Role r2 = new Role("ROLE_ADMIN");

                        roleRepository.saveAll(List.of(r1, r2));

                        User u1 = new User("shyam", "shyamc@locus.sh", "8136818533", "Password123#", new ArrayList<>(),
                                        1, "Vadakara", "Kozhikode", "673507");
                        User u2 = new User("admin", "admin@locus.sh", "8136818533", "Password123#", new ArrayList<>(),
                                        1, "test", "test", "test");

                        u1.setPassword(passwordEncoder.encode(u1.getPassword()));
                        u2.setPassword(passwordEncoder.encode(u2.getPassword()));

                        userRepository.saveAll(List.of(u1, u2));

                        roleService.addRoleToUser(u1.getEmail(), r1.getName());
                        roleService.addRoleToUser(u2.getEmail(), r2.getName());

                        Product p1 = new Product("usd23sk22i", "Levis T Shirt Size(L)", "Black Cotton T Shirt","https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/14890656/2021/7/29/e9d3009d-d7c3-4b00-a865-3ca6f08166e21627549285569-Levis-Men-Tshirts-1961627549285187-1.jpg", 999,
                                        599, 10, 1);
                        Product p2 = new Product("ler67rt234k", "Wrangler Shirt Size(M)", "Red Cotton Shirt","https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/productimage/2021/6/26/cf56ff99-a823-46a1-89d0-f92237dd78711624714986388-1.jpg", 2249,
                                        1799, 5, 1);
                        Product p3 = new Product("jfs33aw93g", "Lee Jeans Size(32)", "Navy Blue Jeans","https://assets.myntassets.com/f_webp,dpr_1.0,q_60,w_210,c_limit,fl_progressive/assets/images/15017184/2021/8/3/5b2f4887-4908-4368-a3ee-90ef9abb256d1627998842336LeeMenBlueSkinnyFitLightFadeJeans1.jpg", 2999, 1999, 20,
                                        1);
                        productRepository.saveAll(List.of(p1, p2, p3));
                };
        }
}
