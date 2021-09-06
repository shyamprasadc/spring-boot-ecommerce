package com.locus.ecommerce.cart;

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
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public void addProduct(Long productId,int quantity) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ApiRequestException("Product Not Found");
        }
        User currentUser = authService.getCurrentUser();
        Cart cart = new Cart(currentUser,product.get(),quantity);
        cartRepository.save(cart);
    }

    public List<Cart> getCartByUser() {
        User currentUser = authService.getCurrentUser();
        return cartRepository.findAllByUser(currentUser);
    }
}
