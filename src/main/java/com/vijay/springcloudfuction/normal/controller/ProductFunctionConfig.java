package com.vijay.springcloudfuction.normal.controller;

import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import com.vijay.springcloudfuction.normal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class ProductFunctionConfig {

    private final ProductService productService;

    @Autowired
    public ProductFunctionConfig(ProductService productService) {
        this.productService = productService;
    }
    @Bean
    public Function<ProductRequest, ProductResponse> createProduct() {
        return productService::create;
    }

    @Bean
    public Function<Long, ProductResponse> getProduct() {
        return productService::getById;
    }

    @Bean
    public Supplier<List<ProductResponse>> getAllProducts() {
        return productService::getAll;
    }

    @Bean
    public Function<ProductRequest, ProductResponse> updateProduct() {
        return request -> productService.update(request.getId(), request);
    }

    @Bean
    public Function<Long, Void> deleteProduct() {
        return id -> {
            productService.delete(id);
            return null;
        };
    }

    // With response entity

   /* @Bean
    public Function<ProductRequest, ResponseEntity<ProductResponse>> createProduct() {
        return request -> ResponseEntity.ok(productService.create(request));
    }

    @Bean
    public Function<Long, ResponseEntity<ProductResponse>> getProduct() {
        return id -> ResponseEntity.ok(productService.getById(id));
    }

    @Bean
    public Supplier<ResponseEntity<List<ProductResponse>>> getAllProducts() {
        return () -> ResponseEntity.ok(productService.getAll());
    }

    @Bean
    public Function<ProductRequest, ResponseEntity<ProductResponse>> updateProduct() {
        return request -> ResponseEntity.ok(productService.update(request.getId(), request));
    }

    @Bean
    public Function<Long, ResponseEntity<Void>> deleteProduct() {
        return id -> {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        };
    }
*/

}
