package com.locus.ecommerce.wishlist;

import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.product.Product;
import com.locus.ecommerce.product.ProductRepository;
import com.locus.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthService authService;

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
        return wishlistRepository.findAllByUserOrderByCreatedAtDesc(currentUser);
    }

    public void removeWishlistItem(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}
