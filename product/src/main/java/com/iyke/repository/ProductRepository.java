package com.iyke.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.db.Database;

public class ProductRepository extends Database implements Repository<Product, UUID> {

    @Override
    public Optional<Product> add(Product value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public List<Product> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Product> findBy(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public Optional<Product> remove(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    
}
