package com.locus.ecommerce.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getOrdersByUser() {
        return orderService.getOrdersByUser();
    }

    @PostMapping
    public void createOrder(@RequestBody Map<String, Object> reqBody) {
        Long addressId = Long.parseLong(reqBody.get("addressId").toString());
        String processId = reqBody.get("processId").toString();
        orderService.createOrder(addressId, processId);
    }
}
