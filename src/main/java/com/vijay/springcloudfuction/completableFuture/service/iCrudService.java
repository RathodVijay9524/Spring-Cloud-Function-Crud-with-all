package com.vijay.springcloudfuction.completableFuture.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface iCrudService <Req,Res,ID> {
    CompletableFuture<Res> create(Req request);
    CompletableFuture<Res> getById(ID id);
    CompletableFuture<List<Res>> getAll();
    CompletableFuture<Res> update(ID id, Req request);
    CompletableFuture<Void> delete(ID id);

}
