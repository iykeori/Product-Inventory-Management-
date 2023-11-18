package com.iyke;

import java.util.Optional;
import java.util.UUID;

import com.iyke.bean.Product;
import com.iyke.service.ProductService;

public class App { 

    static void testProduct() {
        ProductService ps = new ProductService();

        Product prod = new Product();
        prod.setId(UUID.randomUUID());
        prod.setName("Toothpaste");
        prod.setCategory("Health/wellness");
        prod.setSellingPrice(45);
        prod.setCostPrice(25);
        prod.setstockCount(200);
        prod.setManufacturer("closeup");

        Optional<Product> createdProduct = ps.create(prod);
        if(createdProduct.isPresent()){
            System.out.println("Created Product: " + createdProduct);
        }else{
            System.out.println("Not Created");
        }
    }
    public static void main( String[] args )
    {
        testProduct();
    }
}
