package com.locus.ecommerce;

import com.locus.ecommerce.address.AddressService;
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
                                        ProductRepository productRepository, RoleService roleService, AddressService addressService, PasswordEncoder passwordEncoder) {
        return args -> {
            Role r1 = new Role("ROLE_CUSTOMER");
            Role r2 = new Role("ROLE_ADMIN");

            roleRepository.saveAll(List.of(r1, r2));

            User u1 = new User("shyam", "shyamc@locus.sh", "8136818533", "Password123#");
            User u2 = new User("admin", "admin@locus.sh", "8136818533", "Password123#");

            u1.setStatus(1);
            u2.setStatus(1);

            u1.setPassword(passwordEncoder.encode(u1.getPassword()));
            u2.setPassword(passwordEncoder.encode(u2.getPassword()));

            userRepository.saveAll(List.of(u1, u2));

            roleService.addRoleToUser(u1.getEmail(), r1.getName());
            roleService.addRoleToUser(u2.getEmail(), r2.getName());

            addressService.addAddressOfUser(u1.getEmail(), "vadakara", "kozhikode", "673507");
            addressService.addAddressOfUser(u2.getEmail(), "test", "test", "000000");

            Product p1 = new Product(
                    0,
                    "usd23sk22i",
                    "Levis T Shirt",
                    "Black Cotton T Shirt",
                    "black",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14890656/2021/7/29/e9d3009d-d7c3-4b00-a865-3ca6f08166e21627549285569-Levis-Men-Tshirts-1961627549285187-1.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14890656/2021/7/29/5d3545b9-46fb-4d7f-812d-445a808f2e361627549285550-Levis-Men-Tshirts-1961627549285187-3.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14890656/2021/7/29/bc3301f3-4f26-48cf-b5f3-da2c58923be61627549285530-Levis-Men-Tshirts-1961627549285187-5.jpg",
                    999,
                    599,
                    10,
                    1
            );
            Product p2 = new Product(
                    0,
                    "jfs33aw93g",
                    "Lee Jeans",
                    "Navy Blue Jeans",
                    "blue",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/15017184/2021/8/3/5b2f4887-4908-4368-a3ee-90ef9abb256d1627998842336LeeMenBlueSkinnyFitLightFadeJeans1.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/15017184/2021/8/3/f14c1696-7d0b-449c-985b-96f2f20586161627998842653LeeMenBlueSkinnyFitLightFadeJeans4.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/15017184/2021/8/3/2a1db106-b315-4f52-89a8-00c31924562c1627998842554LeeMenBlueSkinnyFitLightFadeJeans3.jpg",
                    2999,
                    1999,
                    20,
                    1
            );
            Product p3 = new Product(
                    0,
                    "ols56uc43s",
                    "U.S. Polo Jacket",
                    "Men Black Bomber Jacket",
                    "black",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12916506/2020/12/9/021b2313-5e33-4249-8370-bb6569cacb5c1607504976384-US-Polo-Assn-Men-Jackets-2391607504974033-1.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12916506/2020/12/9/be5701e9-493a-44b0-bb04-fb84ed72e00a1607504976229-US-Polo-Assn-Men-Jackets-2391607504974033-4.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12916506/2020/12/9/b03fab8c-7f71-403c-b74d-bbd4ad2681b31607504976323-US-Polo-Assn-Men-Jackets-2391607504974033-2.jpg",
                    6499,
                    3249,
                    10,
                    1
            );
            Product p4 = new Product(
                    1,
                    "76ndk29nc5",
                    "Harvard T Shirt",
                    "Men Red Solid Round Neck T-shirt",
                    "red",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12151956/2020/9/18/28389d29-d43b-4c69-9059-3d1d7a2ac85f1600420152479-Harvard-Men-Tshirts-5721600420150498-1.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12151956/2020/9/18/1c2d6f43-ee9c-49ce-be15-add9e86f1dd21600420152288-Harvard-Men-Tshirts-5721600420150498-4.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12151956/2020/9/18/a6016732-9cba-4232-ac89-bddb208edfc31600420152225-Harvard-Men-Tshirts-5721600420150498-5.jpg",
                    599,
                    419,
                    20,
                    1
            );
            Product p5 = new Product(
                    0,
                    "ah43v7843n",
                    "Nike Backpack",
                    "Unisex Grey & Black Backpack",
                    "grey",
                    "https://assets.myntassets.com/f_webp,dpr_2.0,q_60,w_210,c_limit,fl_progressive/assets/images/11547560/2020/9/24/095d7c5b-37e3-49cd-a96b-7e79c1608aa91600918125113-Nike-Unisex-Grey--Black-BRSLA-Backpack-5051600918123485-1.jpg",
                    "https://assets.myntassets.com/f_webp,dpr_2.0,q_60,w_210,c_limit,fl_progressive/assets/images/11547560/2020/9/24/095d7c5b-37e3-49cd-a96b-7e79c1608aa91600918125113-Nike-Unisex-Grey--Black-BRSLA-Backpack-5051600918123485-1.jpg",
                    "https://assets.myntassets.com/f_webp,dpr_2.0,q_60,w_210,c_limit,fl_progressive/assets/images/11547560/2020/9/24/095d7c5b-37e3-49cd-a96b-7e79c1608aa91600918125113-Nike-Unisex-Grey--Black-BRSLA-Backpack-5051600918123485-1.jpg",
                    2495,
                    1871,
                    5,
                    1
            );
            Product p6 = new Product(
                    0,
                    "ler67rt234k",
                    "Wrangler T Shirt",
                    "Red Cotton T Shirt",
                    "red",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2021/6/26/cf56ff99-a823-46a1-89d0-f92237dd78711624714986388-1.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2021/6/26/d83cf50b-c0cd-4c35-a118-b336cf3a797f1624714986423-4.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2021/6/26/ec5d245f-9d72-4f79-b640-51a9ef2398db1624714986434-5.jpg",
                    2249,
                    1799,
                    5,
                    1
            );
            Product p7 = new Product(
                    0,
                    "24sj73jd78",
                    "Puma Sneakers",
                    "Men Black Pacer Styx IDP Sneakers",
                    "black",
                    "https://assets.myntassets.com/f_webp,dpr_2.0,q_60,w_210,c_limit,fl_progressive/assets/images/11334994/2020/5/21/3d167b8c-e00e-4a53-9f3f-b7e4e2f2a4481590080005795PumaMenBlackPacerStyxIDPSneakers1.jpg",
                    "https://assets.myntassets.com/f_webp,dpr_2.0,q_60,w_210,c_limit,fl_progressive/assets/images/11334994/2020/5/21/3d167b8c-e00e-4a53-9f3f-b7e4e2f2a4481590080005795PumaMenBlackPacerStyxIDPSneakers1.jpg",
                    "https://assets.myntassets.com/f_webp,dpr_2.0,q_60,w_210,c_limit,fl_progressive/assets/images/11334994/2020/5/21/3d167b8c-e00e-4a53-9f3f-b7e4e2f2a4481590080005795PumaMenBlackPacerStyxIDPSneakers1.jpg",
                    4499,
                    2024,
                    2,
                    1
            );
            Product p8 = new Product(
                    1,
                    "7bs84ws4n8",
                    "Harvard T Shirt",
                    "Men Grey Solid Round Neck T-shirt",
                    "grey",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12151960/2020/9/18/44345f14-a683-4c4a-8f1b-cbe7826c82ff1600419694000-Harvard-Men-Tshirts-2291600419692278-1.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12151960/2020/9/18/7723fc78-15bc-4a27-8728-0367541b74ad1600419693956-Harvard-Men-Tshirts-2291600419692278-2.jpg",
                    "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12151960/2020/9/18/b9e04968-47d8-4a05-8583-f55a282a18e41600419693803-Harvard-Men-Tshirts-2291600419692278-5.jpg",
                    599,
                    389,
                    20,
                    1
            );
            productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        };
    }
}
