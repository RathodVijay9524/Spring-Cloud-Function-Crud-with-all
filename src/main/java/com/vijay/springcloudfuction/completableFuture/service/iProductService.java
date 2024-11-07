package com.vijay.springcloudfuction.completableFuture.service;

import com.vijay.springcloudfuction.model.ProductRequest;
import com.vijay.springcloudfuction.model.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface iProductService extends iCrudService<ProductRequest, ProductResponse,Long>{
}
