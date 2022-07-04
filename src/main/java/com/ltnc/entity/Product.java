
package com.ltnc.entity;

public class Product {
    private int idProduct;
    private String name;
    private double price;
    private String urlImage;
    private Category category;
    private int quantity;

    public Product() {
    }

    public Product(int idProduct, String name, double price, String urlImage, Category category, int quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
        this.category = category;
        this.quantity = quantity;
    }
    
    public Product(Product p) {
        this(p.getIdProduct(), p.getName(), p.getPrice(), p.getUrlImage(), p.getCategory(), p.getQuantity());
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + idProduct + ", name=" + name + ", price=" + price + ", urlImage=" + urlImage + ", category=" + category + ", quantity=" + quantity + '}';
    }
    
    
}
