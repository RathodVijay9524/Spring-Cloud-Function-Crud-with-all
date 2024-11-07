package com.vijay.springcloudfuction.normal.service.Impl;

import com.vijay.springcloudfuction.entity.Product;
import com.vijay.springcloudfuction.exception.ResourceNotFoundException;
import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import com.vijay.springcloudfuction.repository.ProductRepository;
import com.vijay.springcloudfuction.normal.service.ProductService;
import com.vijay.springcloudfuction.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResponse create(ProductRequest request) {
        Product entity= Mapper.toEntity(request, Product.class,"id");
        productRepository.save(entity);
        return Mapper.toDto(entity,ProductResponse.class);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return Mapper.toDto(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(product -> Mapper.toDto(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Product updatedProduct = Mapper.toEntity(request, Product.class, "id");
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);
        return Mapper.toDto(updatedProduct,ProductResponse.class);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }
        productRepository.deleteById(id);
    }
}
