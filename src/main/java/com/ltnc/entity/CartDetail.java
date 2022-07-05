
package com.ltnc.entity;


public class CartDetail {
    private int quantity;
    private Product product;

    public CartDetail() {
    }

    public CartDetail(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartDetail{" + "quantity=" + quantity + ", product=" + product + '}';
    }
    
    public double getTotalCartDetail() {
        return quantity * product.getPrice();
    }
}
