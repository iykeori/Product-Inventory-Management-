package com.iyke.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.ReturnSales;
import com.iyke.contracts.ServiceContract;
import com.iyke.repository.ReturnSaleRepository;

public class ReturnSaleService implements ServiceContract<ReturnSales, UUID> {

  private ReturnSaleRepository repo;

  public ReturnSaleService() {
    repo = new ReturnSaleRepository();
  }

  @Override
  public Optional<ReturnSales> create(ReturnSales rsale) {
    return repo.add(rsale);
  }

  @Override
  public List<ReturnSales> getAll() {
    return repo.getAll();
  }

  @Override
  public Optional<ReturnSales> findBy(UUID id) {
    return repo.findBy(id);
  }

  @Override
  public Optional<ReturnSales> update(UUID id, ReturnSales value) {
    return null;
  }

  @Override
  public Optional<ReturnSales> delete(UUID id) {
    return null;
  }

}
