package com.iyke.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Sales;
import com.iyke.contracts.ServiceContract;
import com.iyke.repository.SalesRepository;

public class SalesService implements ServiceContract<Sales, UUID> {

  private SalesRepository repo;

  public SalesService() {
    this.repo = new SalesRepository();
  }

  @Override
  public Optional<Sales> create(Sales sales) {
    return repo.add(sales);
  }

  @Override
  public List<Sales> getAll() {
    return repo.getAll();
  }

  @Override
  public Optional<Sales> findBy(UUID id) {
    return repo.findBy(id);
  }

  @Override
  public Optional<Sales> update(UUID id, Sales value) {
    return null;
  }

  @Override
  public Optional<Sales> delete(UUID id) {
    return null;
  }

}
