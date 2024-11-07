package com.vijay.springcloudfuction.webflux.service;

import com.vijay.springcloudfuction.entity.Product;
import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import com.vijay.springcloudfuction.repository.ProductRepository;
import com.vijay.springcloudfuction.util.Mapper;
import reactor.core.publisher.Mono;

import java.util.List;

public class ProductServiceImpl01 implements ProductService01 {
    private final ProductRepository productRepository;

    public ProductServiceImpl01(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<ProductResponse> create(ProductRequest request) {
        Product entity = Mapper.toEntity(request, Product.class,"id");
        // need to do reactive save operations - need reactive repository../
        return null;
    }

    @Override
    public Mono<ProductResponse> getById(Long aLong) {
        return null;
    }

    @Override
    public Mono<List<ProductResponse>> getAll() {
        return null;
    }

    @Override
    public Mono<ProductResponse> update(Long aLong, ProductRequest request) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long aLong) {
        return null;
    }
}
