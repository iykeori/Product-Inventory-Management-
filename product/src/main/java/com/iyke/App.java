package com.iyke;

import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.bean.ReturnSales;
import com.iyke.bean.Sales;
import com.iyke.service.ProductService;
import com.iyke.service.ReturnSaleService;
import com.iyke.service.SalesService;
import com.iyke.util.Operations;

public class App {

  static void testProduct() {
    ProductService ps = new ProductService();

    Product prod = new Product();
    // prod.setId(UUID.randomUUID());
    // prod.setName("Toothpaste");
    // prod.setCategory("Health/wellness");
    // prod.setSellingPrice(45);
    // prod.setCostPrice(25);
    // prod.setstockCount(500);
    // prod.setManufacturer("closeup");

    // Optional<Product> createdProduct = ps.create(prod);
    // if (createdProduct.isPresent()) {
    //   System.out.println("Created Product: " + createdProduct);
    // } else {
    //   System.out.println("Product Not Created");
    // }

    prod.setId(UUID.randomUUID());
    prod.setName("vitamin-c");
    prod.setCategory("Drugs");
    prod.setSellingPrice(45);
    prod.setCostPrice(35);
    prod.setstockCount(400);
    prod.setManufacturer("webber naturals");

    Optional<Product> createdProduct = ps.create(prod);
    if(createdProduct.isPresent()){
      System.out.println("Created Product: " + createdProduct);
    }else{
      System.out.println("Product Not Created");
    }
  }

  static void testSales() {
    SalesService ss = new SalesService();
    ProductService ps = new ProductService();

    Sales sale = new Sales();

    sale.setId(UUID.randomUUID());
    sale.setProduct(ps.findBy(UUID.fromString("1026ec82-3330-4145-b144-c1c88624ae53")).get());
    sale.setQuantity(5);

    Optional<Sales> createdSale = ss.create(sale);
    if (createdSale.isPresent()) {
      System.out.println("Created Sale: " + createdSale);
    } else {
      System.out.println("Sale not created");
    }
  }

  static void testReturnSales() {
    ReturnSaleService rs = new ReturnSaleService();
    ProductService ps = new ProductService();
    SalesService ss = new SalesService();

    ReturnSales rsale = new ReturnSales();

    rsale.setId(UUID.randomUUID());
    rsale.setSale(ss.findBy(UUID.fromString("bfff4ec8-d1b0-43b7-9218-e1815bc049c3")).get());
    rsale.setProduct(rsale.getSale().getProduct());
    rsale.setQuantity(rsale.getSale().getQuantity());

    Optional<ReturnSales> createdRSale = rs.create(rsale);
    if (createdRSale.isPresent()) {
      System.out.println("Created Return Sale: " + createdRSale);
    } else {
      System.out.println(" Return Sale not created");
    }
  }

  static void testOperations() {
    Operations ops = new Operations();
    System.out.println("\n Report on Inventory Balance");
    ops.inventoryBalReport();

    System.out.println("\n Report on Sales");
    ops.salesReport();
    System.out.println("Total Sales Revenue: " + ops.totalSalesRevenue());

    System.out.println("\n Report on Return Sales");
    ops.returnSalesReport();

    System.out.println("Actual Sales Revenue: " + ops.actualSalesRevenue());

  }

  public static void main(String[] args) {
    // testProduct();
    // testSales();
    // testReturnSales();
    testOperations();

  }
}
