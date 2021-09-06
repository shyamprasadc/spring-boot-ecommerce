package com.locus.ecommerce.cart;

import com.locus.ecommerce.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping(path = "api/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Cart> getCartByUser() {
       return cartService.getCartByUser();
    }

    @PostMapping
    public void addProduct(@RequestBody Map<String,Object> reqBody) {
        Long productId = Long.parseLong(reqBody.get("productId").toString()) ;
        int quantity = Integer.parseInt( reqBody.get("quantity").toString());
        cartService.addProduct(productId,quantity);
    }
}
