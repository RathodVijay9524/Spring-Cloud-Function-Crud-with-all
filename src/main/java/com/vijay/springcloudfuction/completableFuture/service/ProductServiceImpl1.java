package com.vijay.springcloudfuction.completableFuture.service;

import com.vijay.springcloudfuction.entity.Product;
import com.vijay.springcloudfuction.exception.ResourceNotFoundException;
import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import com.vijay.springcloudfuction.repository.ProductRepository;
import com.vijay.springcloudfuction.util.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@Primary            //@Qualifier("ProductServiceImpl1")
public class ProductServiceImpl1 implements iProductService {
    private final ProductRepository productRepository;
    private final Executor executor;

    public ProductServiceImpl1(ProductRepository productRepository, Executor executor) {
        this.productRepository = productRepository;
        this.executor = executor;
    }


    @Async
    @Override
    public CompletableFuture<ProductResponse> create(ProductRequest request) {
        Product entity = Mapper.toEntity(request, Product.class, "id");
        productRepository.save(entity);
        return CompletableFuture.completedFuture(Mapper.toDto(entity, ProductResponse.class));
    }

    @Async
    @Override
    public CompletableFuture<ProductResponse> getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return CompletableFuture.completedFuture(Mapper.toDto(product, ProductResponse.class));
    }

    @Async
    @Override
    public CompletableFuture<List<ProductResponse>> getAll() {
        List<ProductResponse> productList = productRepository.findAll().stream()
                .map(product -> Mapper.toDto(product, ProductResponse.class))
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(productList);
    }

    @Async
    @Override
    public CompletableFuture<ProductResponse> update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Product entity = Mapper.toEntity(request, Product.class, "id");
        entity.setId(id);
        productRepository.save(entity);
        return CompletableFuture.completedFuture(Mapper.toDto(entity, ProductResponse.class));
    }

    @Async
    @Override
    public CompletableFuture<Void> delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }
        productRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }
}
