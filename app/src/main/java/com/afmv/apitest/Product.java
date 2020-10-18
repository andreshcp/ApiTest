package com.afmv.apitest;

import java.util.Date;

public class Product {

    String category, description,identification,productName;

    public Product() {
    }

    public Product(String category, String description, String identification, String productName) {
        this.category = category;
        this.description = description;
        this.identification = identification;
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
