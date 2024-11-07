package com.vijay.springcloudfuction.repository;

import com.vijay.springcloudfuction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
