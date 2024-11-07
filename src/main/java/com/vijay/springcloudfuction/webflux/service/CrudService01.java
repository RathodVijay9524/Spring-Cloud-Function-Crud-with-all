package com.vijay.springcloudfuction.webflux.service;


import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudService01<Req, Res, ID> {
    Mono<Res> create(Req request);
    Mono<Res> getById(ID id);
    Mono<List<Res>> getAll();
    Mono<Res> update(ID id, Req request);
    Mono<Void> delete(ID id);
}
