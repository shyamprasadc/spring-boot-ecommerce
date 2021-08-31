package com.locus.ecommerce.product;

import com.locus.ecommerce.exception.ApiRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getOneProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()){
            throw new ApiRequestException("Product Not Found");
        }
        return product;
    }

    public void addNewProduct(Product product) {
        Optional<Product> productBySku = productRepository.findProductBySku(product.getSku());

        if (productBySku.isPresent()) {
            throw new ApiRequestException("Product With SKU Already Exists");
        }
        product.setStatus(1);
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new ApiRequestException("Product Not Found");
        }

        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId, String name, String description, int regularPrice, int discountedPrice, int quantity, int status) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ApiRequestException("Product Not Found"));

        if (name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }
        if (description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)) {
            product.setDescription(description);
        }
        if (regularPrice > 0 && !Objects.equals(product.getRegularPrice(), regularPrice)) {
            product.setRegularPrice(regularPrice);
        }
        if (discountedPrice > 0 && !Objects.equals(product.getDiscountedPrice(), discountedPrice)) {
            product.setDiscountedPrice(discountedPrice);
        }
        if (quantity >= 0 && !Objects.equals(product.getQuantity(), quantity)) {
            product.setQuantity(quantity);
        }
        if (status >= 0 && !Objects.equals(product.getStatus(), status)) {
            product.setRegularPrice(status);
        }
    }

}
