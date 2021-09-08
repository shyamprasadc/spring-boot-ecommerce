package com.locus.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String sku;
    private String name;
    private String description;
    private int regularPrice;
    private int discountedPrice;
    private int quantity;
    private int status;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Product(String sku, String name, String description, int regularPrice, int discountedPrice, int quantity,
            int status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.discountedPrice = discountedPrice;
        this.quantity = quantity;
        this.status = status;
    }
}
