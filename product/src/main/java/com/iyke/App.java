package com.iyke;

import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.bean.Sales;
import com.iyke.service.ProductService;
import com.iyke.service.SalesService;

public class App { 

    static void testProduct() {
        ProductService ps = new ProductService();

        Product prod = new Product();
        prod.setId(UUID.randomUUID());
        prod.setName("Toothpaste");
        prod.setCategory("Health/wellness");
        prod.setSellingPrice(45);
        prod.setCostPrice(25);
        prod.setstockCount(500);
        prod.setManufacturer("closeup");

        Optional<Product> createdProduct = ps.create(prod);
        if(createdProduct.isPresent()){
            System.out.println("Created Product: " + createdProduct);
        }else{
            System.out.println("Product Not Created");
        }
    }

    static void testSales(){
        SalesService ss = new SalesService();
        ProductService ps = new ProductService();

        Sales sale = new Sales();

        sale.setId(UUID.randomUUID());
        sale.setProduct(ps.findBy(UUID.fromString("42cda316-1b4b-4306-8cfc-6c7c79502f47")).get());
        sale.setQuantity(50);

        Optional<Sales> createdSale = ss.create(sale);
        if(createdSale.isPresent()){
            System.out.println("Created Sale: "+ createdSale);
        }else{
            System.out.println("Sale not created");
        }
    }
    public static void main( String[] args ){
        //testProduct();
        testSales();
    }
}
