package com.vijay.springcloudfuction.completableFuture.service;

import com.vijay.springcloudfuction.entity.Product;
import com.vijay.springcloudfuction.exception.ResourceNotFoundException;
import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import com.vijay.springcloudfuction.repository.ProductRepository;
import com.vijay.springcloudfuction.util.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@Qualifier("ProductServiceImpl2")
public class ProductServiceImpl2 implements iProductService{
    private final ProductRepository productRepository;
    private final Executor executor;
    public ProductServiceImpl2(ProductRepository productRepository, Executor executor) {
        this.productRepository = productRepository;
        this.executor = executor;
    }

    @Override
    public CompletableFuture<ProductResponse> create(ProductRequest request) {
        return CompletableFuture.supplyAsync(()->{
            Product entity = Mapper.toEntity(request, Product.class,"id");
            productRepository.save(entity);
            return Mapper.toDto(entity,ProductResponse.class);
        }, executor);
    }

    @Override
    public CompletableFuture<ProductResponse> getById(Long id) {
        return CompletableFuture.supplyAsync(()->{
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            return Mapper.toDto(product,ProductResponse.class);
        }, executor);
    }

    @Override
    public CompletableFuture<List<ProductResponse>> getAll() {
        return CompletableFuture.supplyAsync(()->{
            return productRepository.findAll().stream()
                    .map(product -> Mapper.toDto(product,ProductResponse.class))
                    .collect(Collectors.toList());
        }, executor);
    }

    @Override
    public CompletableFuture<ProductResponse> update(Long id, ProductRequest request) {
        return CompletableFuture.supplyAsync(()->{
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            Product entity = Mapper.toEntity(request, Product.class,"id");
            entity.setId(id);
            productRepository.save(entity);
            return Mapper.toDto(entity,ProductResponse.class);
        },executor);
    }

    @Override
    public CompletableFuture<Void> delete(Long id) {
        return CompletableFuture.runAsync(()->{
            if (!productRepository.existsById(id)) {
                throw new ResourceNotFoundException("Product", "id", id);
            }
            productRepository.deleteById(id);
        },executor);
    }
}
