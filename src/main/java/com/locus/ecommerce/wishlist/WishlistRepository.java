package com.locus.ecommerce.wishlist;

import com.locus.ecommerce.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
   List<Wishlist> findAllByUser(User user) ;
}
