package com.locus.ecommerce.order;

import com.locus.ecommerce.address.Address;
import com.locus.ecommerce.address.AddressRepository;
import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.cart.Cart;
import com.locus.ecommerce.cart.CartRepository;
import com.locus.ecommerce.cart.CartService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.orderProduct.OrderProduct;
import com.locus.ecommerce.orderProduct.OrderProductRepository;
import com.locus.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private AuthService authService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private CartRepository cartRepository;

    public List<Order> getOrdersByUser() {
        User currentUser = authService.getCurrentUser();
        return orderRepository.findAllByUserOrderByCreatedAtDesc(currentUser);
    }

    @Transactional
    public void createOrder(Long addressId, String processId) {
        User currentUser = authService.getCurrentUser();

        Optional<Address> address = addressRepository.findById(addressId);
        if (address.isEmpty()) {
            throw new ApiRequestException("Address Not Found");
        }

        List<Cart> currentUserCart = cartRepository.findAllByUserOrderByCreatedAtDesc(currentUser);
        if (currentUserCart.isEmpty()) {
            throw new ApiRequestException("Cart Is Empty");
        }

        List<OrderProduct> orderProducts = new ArrayList<>();
        int totalPrice = 0;

        for (Cart cart : currentUserCart) {
            orderProducts.add(orderProductRepository.save(new OrderProduct(cart.getProduct(), cart.getQuantity())));
            totalPrice += cart.getProduct().getDiscountedPrice();
        }

        Order order = new Order(currentUser, address.get(), orderProducts, processId, 1, totalPrice);
        orderRepository.save(order);
        cartRepository.deleteAllByUser(currentUser);
    }
}
