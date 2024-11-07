package com.vijay.springcloudfuction.webflux.service;

import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductService01 extends  CrudService01<ProductRequest, ProductResponse, Long> {

}
