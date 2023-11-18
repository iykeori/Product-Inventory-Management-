package main.java.com.iyke.bean;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String category;
    private double sellingPrice;
    private double costPrice; 
    private int stock;

    public Product(){
        this(UUID.randomUUID(), null, null, 0.0, 0.0, 0);
    }
    public Product(UUID id, String name, String category, double sellingPrice, double costPrice, int stock){
        this.id = id;
        this.name = name;
        this.category = category;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.stock = stock;
    }
    //getters and setters
    public UUID getId() {
        return id;
    }
    // public void setId(UUID id) {
    //     this.id = id;
    // }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    //tostring
    @Override
    public String toString() {
        return "Product [id=" + id + "\n name=" + name + "\n category=" + category + "\n sellingPrice=" + sellingPrice
                + "\n costPrice=" + costPrice + "\n stock=" + stock + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        long temp;
        temp = Double.doubleToLongBits(sellingPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(costPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + stock;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (Double.doubleToLongBits(sellingPrice) != Double.doubleToLongBits(other.sellingPrice))
            return false;
        if (Double.doubleToLongBits(costPrice) != Double.doubleToLongBits(other.costPrice))
            return false;
        if (stock != other.stock)
            return false;
        return true;
    }

    
    

    
}
