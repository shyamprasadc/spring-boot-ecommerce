package com.locus.ecommerce.payment;

import com.locus.ecommerce.cart.CartService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/payments")
public class PaymentController {
    @Autowired
    CartService cartService;

    @Value("${razorpay.key}")
    String razorpayKey;
    @Value("${razorpay.secret}")
    String razorpaySecret;

    @PostMapping(path = "/order")
    public Map<String, String> payment() throws RazorpayException {
        int cartTotal = cartService.getCartTotalByUser();
        String uuid =  UUID.randomUUID().toString();

        RazorpayClient razorpay = new RazorpayClient(razorpayKey, razorpaySecret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", cartTotal*100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt",uuid);
        Order razorpayOrder = razorpay.Orders.create(orderRequest);

        Map<String, String> order = new HashMap<>();
        order.put("razorpayOrderId", razorpayOrder.get("id").toString());
        order.put("amount", razorpayOrder.get("amount").toString());
        order.put("receipt",razorpayOrder.get("receipt").toString());
        order.put("status",razorpayOrder.get("status").toString());
        return order;
    }
}
