package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.core.BaseEntity;
import com.floriantoenjes.shop.product.Product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductPurchase extends BaseEntity{
    @ManyToOne
    private Product product;

    private Long quantity;

    public ProductPurchase() {
    }

    public ProductPurchase(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
