package com.locus.ecommerce.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "{productId}")
    public Optional<Product> getOneProduct(@PathVariable("productId") Long productId) {
        return productService.getOneProduct(productId);
    }

    @GetMapping(path = "/groups/{groupId}")
    public List<Product> getProductGroup(@PathVariable("groupId") int groupId) {
        return productService.getProductGroup(groupId);
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
