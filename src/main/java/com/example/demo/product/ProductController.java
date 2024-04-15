package com.example.demo.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @CrossOrigin
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    public void buyProduct(@RequestParam String productName,
                           @RequestParam Integer productQuantity) {

    }

    public void refillProduct(@RequestParam String productName,
                              @RequestParam Integer productQuantity) {

    }
}
