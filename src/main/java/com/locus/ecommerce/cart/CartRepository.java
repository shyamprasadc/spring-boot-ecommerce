package com.locus.ecommerce.cart;

import com.locus.ecommerce.product.Product;
import com.locus.ecommerce.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
    
    Optional<Cart> findByProductAndUser(Product product, User user);

    void deleteAllByUser(User user);
}
