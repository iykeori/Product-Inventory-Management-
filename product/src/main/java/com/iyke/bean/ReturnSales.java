package com.iyke.bean;

import java.util.Date;
import java.util.UUID;

public class ReturnSales {
  private UUID id;
  private Product product;
  private int quantity;
  private double totalPrice;
  private Sales sale;
  private Date created;
  private Date updated;

  public ReturnSales() {
    this(UUID.randomUUID(), null, 0, null, null, null);
  }

  public ReturnSales(UUID id, Product product, int quantity, Sales sale, Date created, Date updated) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
    this.sale = sale;
    this.created = created;
    this.updated = updated;
  }

  // getters and setters

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Sales getSale() {
    return sale;
  }

  public void setSale(Sales sale) {
    this.sale = sale;
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

  // to string
  @Override
  public String toString() {
    return "ReturnSales [id=" + id + "\n product=" + product + "\n quantity=" + quantity + "\n totalPrice="
        + totalPrice + "\n sale=" + sale + "\n created=" + created + "\n updated=" + updated + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    result = prime * result + quantity;
    long temp;
    temp = Double.doubleToLongBits(totalPrice);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((sale == null) ? 0 : sale.hashCode());
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
    ReturnSales other = (ReturnSales) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (product == null) {
      if (other.product != null)
        return false;
    } else if (!product.equals(other.product))
      return false;
    if (quantity != other.quantity)
      return false;
    if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
      return false;
    if (sale == null) {
      if (other.sale != null)
        return false;
    } else if (!sale.equals(other.sale))
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
