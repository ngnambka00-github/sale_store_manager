
package com.ltnc.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int idCart;
    private Customer customer = null;
    private Date dateCreated;
    private Discount discount = null;
    private List<Product> listProduct;

    public Cart() {
        listProduct = new ArrayList<>();
        dateCreated = new Date(new java.util.Date().getTime());
    }

    public Cart(int idCart, Customer customer, Date dateCreated, Discount discount, List<Product> listProduct) {
        this.idCart = idCart;
        this.customer = customer;
        this.dateCreated = dateCreated;
        this.discount = discount;
        this.listProduct = listProduct;
    }

    public Cart(Customer customer, Date dateCreated, Discount discount, List<Product> listProduct) {
        this.customer = customer;
        this.dateCreated = dateCreated;
        this.discount = discount;
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
    
    @Override
    public String toString() {
        return "Cart{" + "idCart=" + idCart + ", customer=" + customer + ", dateCreated=" + dateCreated + ", discount=" + discount + ", listProduct=" + listProduct + '}';
    }

    
    public boolean checkProductInCart(Product product) {
        if (listProduct.isEmpty()) {
            return false;
        }
        
        for (Product p : listProduct) {
            if (p.getIdProduct() == product.getIdProduct()) {
                return true;
            }
        }
        return false;
    }
    
    public List<CartDetail> getListCartDetail() {
        List<CartDetail> listCartDetail = new ArrayList<>();
        
        int sizeOfProduct = listProduct.size();
        boolean[] listCheck = new boolean[sizeOfProduct];
        
        for (int i = 0; i < sizeOfProduct; i++) {
            if (!listCheck[i]) {
                listCheck[i] = true;
                int count = 1;
                CartDetail cd = new CartDetail();
                cd.setProduct(listProduct.get(i));
                
                for (int j = i + 1; j < sizeOfProduct; j++) {
                    if (listProduct.get(i).getIdProduct() == listProduct.get(j).getIdProduct()) {
                        listCheck[j] = true;
                        count += 1;
                    }
                }
                cd.setQuantity(count);
                
                listCartDetail.add(cd);
            }
        }
        
        return listCartDetail;
    }
    
    public double getTotalPriceNotApplyDiscount() {
        double price = 0;
        for (Product p :listProduct) {
            price += p.getPrice();
        }
        return price;
    }
    
    public double getTotalPriceApplyDiscount() {
        if (discount != null) {
            return getTotalPriceNotApplyDiscount() * discount.getPrecentDiscount() / 100 ;
        }
        return getTotalPriceNotApplyDiscount();
    }
}
