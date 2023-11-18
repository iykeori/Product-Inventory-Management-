package com.iyke.bean;

import java.util.Date;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String category;
    private double sellingPrice;
    private double costPrice; 
    private int stockCount;
    private String manufacturer;
    private Date created;
    private Date updated;

    public Product(){
        this(UUID.randomUUID(), null, null, 0.0, 0.0, 0,null, null, null);
    }
    public Product(UUID id, String name, String category, double sellingPrice, double costPrice, int stockCount, String manufacturer, Date created, Date updated){
        this.id = id;
        this.name = name;
        this.category = category;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.stockCount = stockCount;
        this.created = created;
        this.updated = updated;

        
    }

    //getters and setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
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

    public int getstockCount() {
        return stockCount;
    }

    public void setstockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

     //tostring
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", category=" + category + ", sellingPrice=" + sellingPrice
                + ", costPrice=" + costPrice + ", stockCount=" + stockCount + ", manufacturer=" + manufacturer + ", created="
                + created + ", updated=" + updated + "]";
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
        result = prime * result + stockCount;
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((updated == null) ? 0 : updated.hashCode());
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
        if (stockCount != other.stockCount)
            return false;
        if (manufacturer == null) {
            if (other.manufacturer != null)
                return false;
        } else if (!manufacturer.equals(other.manufacturer))
            return false;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (updated == null) {
            if (other.updated != null)
                return false;
        } else if (!updated.equals(other.updated))
            return false;
        return true;
    }
    
}
