
package com.ltnc.entity;

public class Category {
    private int idCategory;
    private String name;
    private String description;
    private String urlImage;

    public Category() {
    }

    public Category(int idCategory, String name, String description, String urlImage) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "Category{" + "idCategory=" + idCategory + ", name=" + name + ", description=" + description + ", urlImage=" + urlImage + '}';
    }
    
}
