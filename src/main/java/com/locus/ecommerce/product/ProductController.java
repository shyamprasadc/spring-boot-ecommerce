package com.locus.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "{productId}")
    public Optional<Product> getOneProduct(@PathVariable("productId") Long productId) {
        return productService.getOneProduct(productId);
    }

    @PostMapping
    public void addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable("productId") Long productId, @RequestBody Map<String, Object> reqBody) {
        String name = (String) reqBody.get("name");
        String description = (String) reqBody.get("description");
        int regularPrice = (int) reqBody.get("regularPrice");
        int discountedPrice = (int) reqBody.get("discountedPrice");
        int quantity = (int) reqBody.get("quantity");
        int status = (int) reqBody.get("status");

        productService.updateProduct(productId, name, description, regularPrice, discountedPrice, quantity, status);
    }
}
