
package com.ltnc.entity;


public class Customer {
    private Integer idCustomer;
    private String name;
    private String phoneNumber;
    private String email;
    private int accumulatedPoint;

    public Customer() {
    }

    public Customer(String name, String phoneNumber, String email, int accumulatedPoint) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accumulatedPoint = accumulatedPoint;
    }
    
    
    public Customer(Integer idCustomer, String name, String phoneNumber, String email, int accumulatedPoint) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accumulatedPoint = accumulatedPoint;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccumulatedPoint() {
        return accumulatedPoint;
    }

    public void setAccumulatedPoint(int accumulatedPoint) {
        this.accumulatedPoint = accumulatedPoint;
    }
    
    

    @Override
    public String toString() {
        return "Customer{" + "idCustomer=" + idCustomer + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }
    
}
