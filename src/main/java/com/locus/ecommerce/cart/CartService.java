package com.locus.ecommerce.cart;

import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.orderProduct.OrderProduct;
import com.locus.ecommerce.product.Product;
import com.locus.ecommerce.product.ProductRepository;
import com.locus.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthService authService;

    @Transactional
    public void addProduct(Long productId, int quantity) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ApiRequestException("Product Not Found");
        }
        User currentUser = authService.getCurrentUser();

        Optional<Cart> existingCartItem = cartRepository.findByProductAndUser(product.get(), currentUser);
        if (existingCartItem.isPresent()) {
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity() + quantity);
        } else {
            Cart cart = new Cart(currentUser, product.get(), quantity);
            cartRepository.save(cart);
        }
    }

    public List<Cart> getCartByUser() {
        User currentUser = authService.getCurrentUser();
        return cartRepository.findAllByUser(currentUser);
    }

    public int getCartTotalByUser(){
        User currentUser = authService.getCurrentUser();
        List<Cart> currentUserCart = cartRepository.findAllByUser(currentUser);
        if(currentUserCart.isEmpty()){
            throw new ApiRequestException("Cart Is Empty");
        }

        int sum = 0;
        for(Cart cart: currentUserCart){
            sum += cart.getProduct().getDiscountedPrice();
        }
        return sum;
    }

    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
