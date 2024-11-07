package com.vijay.springcloudfuction.completableFuture.controller;

import com.vijay.springcloudfuction.completableFuture.service.iProductService;
import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
@Service
public class ProductControllerConfig1 {
    private final iProductService productService;

    @Autowired
    public ProductControllerConfig1(iProductService productService) {
        this.productService = productService;
    }

    @Bean
    public Function<ProductRequest, CompletableFuture<ProductResponse>> createProducts() {
        return productService::create;
    }

    @Bean
    public Function<Long, CompletableFuture<ProductResponse>> getProducts() {
        return productService::getById;
    }

    @Bean
    public Supplier<CompletableFuture<List<ProductResponse>>> getAllProductss() {
        return productService::getAll;
    }

    @Bean
    public Function<ProductRequest, CompletableFuture<ProductResponse>> updateProducts() {
        return request -> productService.update(request.getId(), request);
    }

    @Bean
    public Function<Long, CompletableFuture<Void>> deleteProducts() {
        return id -> {
            productService.delete(id);
            return CompletableFuture.completedFuture(null);
        };

    }
}
