package com.locus.ecommerce.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Cart> getCartByUser() {
        return cartService.getCartByUser();
    }

    @PostMapping
    public void addProduct(@RequestBody Map<String, Object> reqBody) {
        Long productId = Long.parseLong(reqBody.get("productId").toString());
        int quantity = Integer.parseInt(reqBody.get("quantity").toString());
        cartService.addProduct(productId, quantity);
    }

    @DeleteMapping
    public void removeCartItem(@RequestBody Map<String, Object> reqBody) {
        Long cartId = Long.parseLong(reqBody.get("cartId").toString());
        cartService.removeCartItem(cartId);
    }
}
