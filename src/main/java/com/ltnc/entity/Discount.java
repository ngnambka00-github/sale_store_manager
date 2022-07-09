package com.ltnc.entity;

import java.sql.Date;


public class Discount {
    private int idDiscount;
    private String name;
    private String description;
    private double precentDiscount;
    private Date start;
    private Date end;

    public Discount() {
    }

    public Discount(int idDiscount, String name, String description, double precentDiscount, Date start, Date end) {
        this.idDiscount = idDiscount;
        this.name = name;
        this.description = description;
        this.precentDiscount = precentDiscount;
        this.start = start;
        this.end = end;
    }

    public Discount(String name, String description, double precentDiscount, Date start, Date end) {
        this.name = name;
        this.description = description;
        this.precentDiscount = precentDiscount;
        this.start = start;
        this.end = end;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrecentDiscount() {
        return precentDiscount;
    }

    public void setPrecentDiscount(double precentDiscount) {
        this.precentDiscount = precentDiscount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Discount{" + "idDiscount=" + idDiscount + ", name=" + name + ", description=" + description + ", precentDiscount=" + precentDiscount + ", start=" + start + ", end=" + end + '}';
    }
       
}
