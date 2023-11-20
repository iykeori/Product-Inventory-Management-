package com.iyke.util;

import java.util.ArrayList;
import java.util.List;

import com.iyke.bean.Product;
import com.iyke.bean.ReturnSales;
import com.iyke.bean.Sales;
import com.iyke.repository.ProductRepository;
import com.iyke.repository.ReturnSaleRepository;
import com.iyke.repository.SalesRepository;

public class Operations {

  private ProductRepository productRepo;
  private SalesRepository salesRepo;
  private ReturnSaleRepository returnRepo;

  public Operations() {
    productRepo = new ProductRepository();
    salesRepo = new SalesRepository();
    returnRepo = new ReturnSaleRepository();
  }

  // Report on Inventory Balance
  public void inventoryBalReport() {
    List<Product> list = new ArrayList<>();
    list = productRepo.getAll();
    // check if list from db is empty
    if (list != null) {
      int count = 0;
      // loop through list
      for (Product product : list) {
        System.out.println(++count + ". Product name:  " + product.getName() + " | Stock Count: "
            + product.getstockCount() + " | Total Price: " + product.getstockCount() * product.getCostPrice());
      }
    } else {
      System.out.println("Inventory Report is empty");
    }
  }

  // Report on Sales
  public void salesReport() {
    List<Sales> list = new ArrayList<>();
    list = salesRepo.getAll();
    int count = 0;
    if (list != null) {
      for (Sales sales : list) {
        System.out.println(++count + ". Product name:  " + sales.getProduct().getName() + " | Quantity "
            + sales.getQuantity() + " | Price: " + sales.getTotalPrice());
      }
    } else {
      System.out.println("Sales Report is empty");
    }
  }

  // Report on returnsales
  public void returnSalesReport() {
    List<ReturnSales> list = new ArrayList<>();
    list = returnRepo.getAll();
    int count = 0;
    if (list != null) {
      for (ReturnSales rsales : list) {
        System.out.println(++count + ". Product name:  " + rsales.getProduct().getName() + " | Quantity "
            + rsales.getQuantity() + " | Price: " + rsales.getTotalPrice());
      }
    } else {
      System.out.println("Return Sales Report is empty");
    }
  }

  // Report on Total revenue
  public Double totalSalesRevenue() {
    List<Sales> list = new ArrayList<>();
    list = salesRepo.getAll();
    double totalSalesPrice = 0.0;
    if (list != null) {
      for (Sales sales : list) {
        totalSalesPrice += sales.getTotalPrice();
      }
    } else {
      System.out.println("No Sales Revenue");
    }
    return totalSalesPrice;
  }

  // Report on actual revenue
  public Double actualSalesRevenue() {
    List<ReturnSales> list = new ArrayList<>();
    list = returnRepo.getAll();
    double totalReturnSalesPrice = 0.0;
    if (list != null) {
      for (ReturnSales rsales : list) {
        totalReturnSalesPrice += rsales.getTotalPrice();
      }
    } else {
      System.out.println("No Actual Sales Revenue");
    }
    return totalSalesRevenue() - totalReturnSalesPrice;
  }
}
