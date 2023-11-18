package com.iyke.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.db.Database;

public class ProductRepository extends Database implements Repository<Product, UUID> {

    private ResultSet resultSet;

    @Override
    public Optional<Product> add(Product product) {
        String sql = "";
        int inserted = -1;
        
        if (findBy(product.getId()).isPresent()){
            sql = "UPDATE product SET name=?, category=?, sellingPrice=?, costPrice=?, stock=?";
            inserted = postQuery(sql, product.getName(), product.getCategory(), product.getSellingPrice(), product.getCostPrice(), product.getStock());
        }else{
            sql = "INSERT INTO product (id, name, category, sellingPrice, costPrice, stock) VALUES (?,?,?,?,?,?)";
            inserted = postQuery(sql,product.getId().toString(), product.getName(), product.getCategory(), product.getSellingPrice(), product.getCostPrice(), product.getStock());
        }
        if(inserted != -1) return Optional.ofNullable(findBy(product.getId()).get());

        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM product;";
            resultSet = getQuery(sql);
            while (resultSet.next()) {
                Product product = new Product();

                product.setId(UUID.fromString(resultSet.getString(1)));
                product.setName(resultSet.getString(2));
                product.setCategory(resultSet.getString(3));
                product.setSellingPrice(resultSet.getDouble(4));
                product.setCostPrice(resultSet.getDouble(5));
                product.setStock(resultSet.getInt(6));
                product.setManufacturer(resultSet.getString(7));
                product.setCreated(resultSet.getDate(8));
                product.setUpdated(resultSet.getDate(9));
                
                list.add(product);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Product> findBy(UUID id) {
        try {
            String sql = "SELECT * FROM product WHERE product.id = ?";
            resultSet = getQuery(sql, id.toString());
            if (resultSet.next()) {
              Product product = new Product();
              
              product.setId(UUID.fromString(resultSet.getString(1)));
              product.setName(resultSet.getString(2));
              product.setCategory(resultSet.getString(3));
              product.setSellingPrice(resultSet.getDouble(4));
              product.setCostPrice(resultSet.getDouble(5));
              product.setStock(resultSet.getInt(6));
              product.setManufacturer(resultSet.getString(7));
              product.setCreated(resultSet.getDate(8));
              product.setUpdated(resultSet.getDate(9));
      
              return Optional.ofNullable(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> remove(UUID id) {
        Optional<Product> product = findBy(id);
        if (product.isPresent()) {
        try {
            String sql = "DELETE FROM product WHERE product.id = ?";
            int result = postQuery(sql, id);
            if (result != -1 && result > 0) {
            return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        return Optional.empty();
    }

    //find by name, category and manufacturer
    
}
