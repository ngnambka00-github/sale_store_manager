
package com.ltnc.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int idCart;
    private Customer customer;
    private Date dateCreated;
    private List<Product> listProduct;

    public Cart() {
        listProduct = new ArrayList<>();
    }

    public Cart(int idCart, Customer customer, Date dateCreated, List<Product> listProduct) {
        this.idCart = idCart;
        this.customer = customer;
        this.dateCreated = dateCreated;
        this.listProduct = listProduct;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return "Cart{" + "idCart=" + idCart + ", customer=" + customer + ", dateCreated=" + dateCreated + ", listProduct=" + listProduct + '}';
    }
    
}
