package com.locus.ecommerce.wishlist;

import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.product.Product;
import com.locus.ecommerce.product.ProductRepository;
import com.locus.ecommerce.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public void addProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ApiRequestException("Product Not Found");
        }
        User currentUser = authService.getCurrentUser();
        Wishlist wishlist = new Wishlist(currentUser, product.get());
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getWishlistByUser() {
        User currentUser = authService.getCurrentUser();
        return wishlistRepository.findAllByUser(currentUser);
    }

    public void removeWishlistItem(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}
