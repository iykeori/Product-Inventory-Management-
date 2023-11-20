package com.iyke.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.bean.Sales;
import com.iyke.db.Database;

public class SalesRepository extends Database implements Repository<Sales, UUID> {

  private ResultSet resultSet;
  private ProductRepository productRepo;

  public SalesRepository() {
    productRepo = new ProductRepository();
  }

  @Override
  public Optional<Sales> add(Sales sale) {
    String sql = "";
    int inserted = -1;
    Product product = sale.getProduct();

    if (product.getstockCount() >= sale.getQuantity()) {

      /**
       * ⚠️ TODO: record sales before updating inventory
       */

      product.setstockCount(product.getstockCount() - sale.getQuantity());
      // update product
      Optional<Product> prod = productRepo.add(product);
      // check if update was sucessfull before proceeding
      if (prod.isPresent()) {
        sql = "INSERT INTO sales (id, productId, quantity, totalPrice) VALUES (?,?,?,?)";
        inserted = postQuery(sql, sale.getId().toString(), product.getId().toString(), sale.getQuantity(), product.getSellingPrice() * sale.getQuantity());
      }
    } else {
      System.out.println("\nQuantity of Product remaining: " + product.getstockCount());
    }
    if (inserted != -1)
      return Optional.ofNullable(findBy(sale.getId()).get());

    return Optional.empty();

  }

  @Override
  public List<Sales> getAll() {
    List<Sales> list = new ArrayList<>();

    try {
      String sql = "SELECT * FROM sales";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        Sales sale = new Sales();

        sale.setId(UUID.fromString(resultSet.getString(1)));
        sale.setProduct(productRepo.findBy(UUID.fromString(resultSet.getString(2))).get());
        sale.setQuantity(resultSet.getInt(3));
        sale.setTotalPrice(resultSet.getDouble(4));
        sale.setCreated(resultSet.getDate(5));
        sale.setUpdated(resultSet.getDate(6));

        list.add(sale);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public Optional<Sales> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM sales WHERE sales.id = ?";
      return this.fetch(sql, id.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<Sales> remove(UUID id) {
    Optional<Sales> sale = findBy(id);
    if (sale.isPresent()) {
      try {
        String sql = "DELETE FROM sales WHERE sales.id = ?";
        int result = postQuery(sql, id.toString());
        if (result != -1 && result > 0) {
          return sale;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

  // find sales by productId
  public Optional<Sales> findByProductId(UUID id) {
    try {
      String sql = "SELECT * FROM sales WHERE sales.productId = ?";
      return this.fetch(sql, id.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  // fetch
  private Optional<Sales> fetch(String sql, Object value) throws SQLException {
    resultSet = getQuery(sql, value.toString());
    if (resultSet.next()) {
      Sales sale = new Sales();

      sale.setId(UUID.fromString(resultSet.getString(1)));
      sale.setProduct(productRepo.findBy(UUID.fromString(resultSet.getString(2))).get());
      sale.setQuantity(resultSet.getInt(3));
      sale.setTotalPrice(resultSet.getDouble(4));
      sale.setCreated(resultSet.getDate(5));
      sale.setUpdated(resultSet.getDate(6));

      return Optional.ofNullable(sale);
    }
    return Optional.empty();
  }

}
