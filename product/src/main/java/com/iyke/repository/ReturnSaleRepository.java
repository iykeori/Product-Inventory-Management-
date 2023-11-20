package com.iyke.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.bean.ReturnSales;
import com.iyke.db.Database;

public class ReturnSaleRepository extends Database implements Repository<ReturnSales, UUID> {

  private ResultSet resultSet;
  private SalesRepository salesRepo;
  private ProductRepository productRepo;

  public ReturnSaleRepository() {
    salesRepo = new SalesRepository();
    productRepo = new ProductRepository();
  }

  @Override
  public Optional<ReturnSales> add(ReturnSales returnSales) {
    String sql = "";
    int inserted = -1;

    // confirm is sale id is in db
    if (salesRepo.findBy(returnSales.getSale().getId()).isPresent()) {
      // check to make sure the sales have not been return before
      if (!findBySaleId(returnSales.getSale().getId()).isPresent()) {
        /**
         * ⚠️ TODO: record returned sales before updating inventory
         */


        Product product = returnSales.getProduct();//get the sold product

        //set stock count
        product.setstockCount(product.getstockCount() + returnSales.getQuantity());

        // Update product inventory
        Optional<Product> prod = productRepo.add(product);

        if (prod.isPresent()) {
          // Insert in sales db
          sql = "INSERT INTO returnsales (id, productId, salesId , quantity, totalPrice) VALUES (?,?,?,?,?)";
          inserted = postQuery(sql, returnSales.getId().toString(), product.getId().toString(), returnSales.getSale().getId().toString(), returnSales.getQuantity(), product.getSellingPrice() * returnSales.getQuantity());
        }
      } else {
        System.out.println("\nSale has been returned before.");
      }
    } else {
      System.out.println("\nSales information not found");
    }

    if (inserted != -1)
      return Optional.ofNullable(findBy(returnSales.getId()).get());

    return Optional.empty();
  }

  @Override
  public List<ReturnSales> getAll() {
    List<ReturnSales> list = new ArrayList<>();
    try {
      String sql = "SELECT * FROM returnsales";
      resultSet = getQuery(sql);
      while (resultSet.next()) {
        ReturnSales returnSales = new ReturnSales();

        returnSales.setId(UUID.fromString(resultSet.getString(1)));
        returnSales.setProduct(productRepo.findBy(UUID.fromString(resultSet.getString(2))).get());
        returnSales.setSale(salesRepo.findBy(UUID.fromString(resultSet.getString(3))).get());
        returnSales.setQuantity(resultSet.getInt(4));
        returnSales.setTotalPrice(resultSet.getDouble(5));
        returnSales.setCreated(resultSet.getDate(6));
        returnSales.setUpdated(resultSet.getDate(7));

        list.add(returnSales);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public Optional<ReturnSales> findBy(UUID id) {
    try {
      String sql = "SELECT * FROM returnsales WHERE returnsales.id = ?";
      return this.fetch(sql, id.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public Optional<ReturnSales> findBySaleId(UUID id) {
    try {
      String sql = "SELECT * FROM returnsales WHERE returnsales.salesId = ?";
      return this.fetch(sql, id.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<ReturnSales> remove(UUID id) {
    Optional<ReturnSales> rsale = findBy(id);
    if (rsale.isPresent()) {
      try {
        String sql = "DELETE FROM returnsales WHERE returnsales.id = ?";
        int result = postQuery(sql, id.toString());
        if (result != -1 && result > 0) {
          return rsale;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

  // fetch
  private Optional<ReturnSales> fetch(String sql, Object value) throws SQLException {
    resultSet = getQuery(sql, value.toString());

    if (resultSet.next()) {
      ReturnSales returnSales = new ReturnSales();

      returnSales.setId(UUID.fromString(resultSet.getString(1)));
      returnSales.setProduct(productRepo.findBy(UUID.fromString(resultSet.getString(2))).get());
      returnSales.setSale(salesRepo.findBy(UUID.fromString(resultSet.getString(3))).get());
      returnSales.setQuantity(resultSet.getInt(4));
      returnSales.setTotalPrice(resultSet.getDouble(5));
      returnSales.setCreated(resultSet.getDate(6));
      returnSales.setUpdated(resultSet.getDate(7));

      return Optional.ofNullable(returnSales);
    }
    return Optional.empty();
  }

}
