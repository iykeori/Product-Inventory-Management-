package com.iyke.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.contracts.ServiceContract;
import com.iyke.repository.ProductRepository;

public class ProductService implements ServiceContract<Product, UUID> {

    private ProductRepository repo;

    public ProductService(){
        this.repo = new ProductRepository();
    }

    @Override
    public Optional<Product> create(Product product) {
        return repo.add(product);
    }

    @Override
    public List<Product> getAll() {
        return repo.getAll();
    }

    @Override
    public Optional<Product> findBy(UUID id) {
        return repo.findBy(id);
    }

    @Override
    public Optional<Product> update(UUID id, Product product) {
        Product oldProduct = repo.findBy(id).get();
        oldProduct.setName(product.getName());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setCostPrice(product.getCostPrice());
        oldProduct.setSellingPrice(product.getSellingPrice());
        oldProduct.setStock(product.getStock());
        oldProduct.setManufacturer(product.getManufacturer());
        return repo.add(oldProduct);
    }

    @Override
    public Optional<Product> delete(UUID id) {
        return repo.remove(id);
    }
    
}
