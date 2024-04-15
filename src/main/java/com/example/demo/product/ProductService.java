package com.example.demo.product;

import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final static String PRODUCT_NOT_FOUND_MSG = "Product with name %s not found";
    private final static String PRODUCT_ALREADY_EXISTS_MSG = "Product with name %s already exists";
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        boolean exists = productRepository.findByProductName(product.getProductName()).isPresent();
        if (exists) {
            throw new IllegalArgumentException(String.format(PRODUCT_ALREADY_EXISTS_MSG, product.getProductName()));
        }
        productRepository.save(product);
    }
}
