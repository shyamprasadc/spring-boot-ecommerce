package com.locus.ecommerce.wishlist;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

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
