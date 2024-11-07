package com.vijay.springcloudfuction.normal.service;

import java.util.List;

public interface CrudService<Req, Res, ID> {
    Res create(Req request);
    Res getById(ID id);
    List<Res> getAll();
    Res update(ID id, Req request);
    void delete(ID id);
}