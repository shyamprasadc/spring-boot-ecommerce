package com.locus.ecommerce.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public List<Wishlist> getWishlistByUser() {
        return wishlistService.getWishlistByUser();
    }

    @PostMapping
    public void addProduct(@RequestBody Map<String, Object> reqBody) {
        Long productId = Long.parseLong(reqBody.get("productId").toString());
        wishlistService.addProduct(productId);
    }

    @DeleteMapping
    public void removeWishlistItem(@RequestBody Map<String, Object> reqBody) {
        Long wishlistId = Long.parseLong(reqBody.get("wishlistId").toString());
        wishlistService.removeWishlistItem(wishlistId);
    }
}
