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
    private int groupId;
    private String sku;
    private String name;
    private String description;
    private String color;
    private String image;
    private String image2;
    private String image3;
    private int regularPrice;
    private int discountedPrice;
    private int quantity;
    private int status;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Product(
            int groupId, String sku, String name, String description, String color, String image, String image2, String image3, int regularPrice, int discountedPrice, int quantity,
            int status) {
        this.groupId = groupId;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.color = color;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.regularPrice = regularPrice;
        this.discountedPrice = discountedPrice;
        this.quantity = quantity;
        this.status = status;
    }
}
