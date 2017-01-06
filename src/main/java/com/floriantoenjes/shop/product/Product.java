package com.floriantoenjes.shop.product;

import com.floriantoenjes.shop.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Product extends BaseEntity {
    private String name;

    private Double price;

    private Long stockQuantity;

    public Product() {
    }

    public Product(String name, Double price, Long stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
