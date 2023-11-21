package com.iyke.util;

import java.util.ArrayList;
import java.util.List;

import com.iyke.bean.Product;
import com.iyke.bean.ReturnSales;
import com.iyke.bean.Sales;
import com.iyke.service.ProductService;
import com.iyke.service.ReturnSaleService;
import com.iyke.service.SalesService;

public class Operations {
  private ProductService productService;
  private SalesService salesService;
  private ReturnSaleService rService;

  public Operations() {
    productService = new ProductService();
    salesService = new SalesService();
    rService = new ReturnSaleService();
  }

  // Report on Inventory Balance
  public void inventoryBalReport() {
    List<Product> list = productService.getAll();
    // check if list from db is empty
    if (list.size() != 0) {
      int count = 0;
      // loop through list
      for (Product product : list) {
        System.out.println(++count + ". Product name:  " + product.getName() + " | Stock Count: " + product.getstockCount() + " | Total Price: " + product.getstockCount() * product.getCostPrice());
      }
    } else {
      System.out.println("Inventory Report is empty");
    }
  }

  // Report on Sales
  public void salesReport() {
    List<Sales> list = salesService.getAll();
    int count = 0;
    if (list.size() != 0) {
      for (Sales sales : list) {
        System.out.println(++count + ". Product name:  " + sales.getProduct().getName() + " | Quantity " + sales.getQuantity() + " | Price: " + sales.getTotalPrice());
      }
    } else {
      System.out.println("Sales Report is empty");
    }
  }

  // Report on returnsales
  public void returnSalesReport() {
    List<ReturnSales> list = rService.getAll();
    int count = 0;
    if (list.size() != 0) {
      for (ReturnSales rsales : list) {
        System.out.println(++count + ". Product name:  " + rsales.getProduct().getName() + " | Quantity " + rsales.getQuantity() + " | Price: " + rsales.getTotalPrice());
      }
    } else {
      System.out.println("Return Sales Report is empty");
    }
  }

  // Report on Total revenue
  public Double totalSalesRevenue() {
    List<Sales> list = salesService.getAll();
    double totalSalesPrice = 0.0;
    if (list.size() != 0) {
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
    List<ReturnSales> list = rService.getAll();
    double totalReturnSalesPrice = 0.0;
    if (list.size() != 0) {
      for (ReturnSales rsales : list) {
        totalReturnSalesPrice += rsales.getTotalPrice();
      }
    } else {
      System.out.println("No Actual Sales Revenue");
    }
    return totalSalesRevenue() - totalReturnSalesPrice;
  }
}
