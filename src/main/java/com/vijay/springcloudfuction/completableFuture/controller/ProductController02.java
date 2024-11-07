package com.vijay.springcloudfuction.completableFuture.controller;
import com.vijay.springcloudfuction.completableFuture.service.iProductService;
import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController02 {

    private final iProductService productService;

    public ProductController02(@Qualifier("ProductServiceImpl2") iProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<ProductResponse>> createProduct(@RequestBody ProductRequest request) {
        return productService.create(request)
                .thenApply(productResponse -> new ResponseEntity<>(productResponse, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<ProductResponse>> getProductById(@PathVariable Long id) {
        return productService.getById(id)
                .thenApply(productResponse -> new ResponseEntity<>(productResponse, HttpStatus.OK));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<ProductResponse>>> getAllProducts() {
        return productService.getAll()
                .thenApply(products -> new ResponseEntity<>(products, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<ProductResponse>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.update(id, request)
                .thenApply(productResponse -> new ResponseEntity<>(productResponse, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProduct(@PathVariable Long id) {
        return productService.delete(id)
                .thenApply(aVoid -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}

