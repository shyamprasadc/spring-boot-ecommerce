package com.locus.ecommerce.order;

import com.locus.ecommerce.address.Address;
import com.locus.ecommerce.orderProduct.OrderProduct;
import com.locus.ecommerce.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<OrderProduct> orderProducts = new ArrayList<>();
    private String processId;
    private int status;
    private int totalPrice;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Order(User user, Address address, Collection<OrderProduct> orderProducts, String processId, int status, int totalPrice) {
        this.user = user;
        this.address = address;
        this.orderProducts = orderProducts;
        this.processId = processId;
        this.status = status;
        this.totalPrice = totalPrice;
    }
}
