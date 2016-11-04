package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.core.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
public class Purchase extends BaseEntity {
    @OneToMany
    private List<ProductPurchase> productPurchases;

    public Purchase() {
        productPurchases = new ArrayList<>();
    }

    public List<ProductPurchase> getProductPurchases() {
        return productPurchases;
    }

    public void setProductPurchases(List<ProductPurchase> productPurchases) {
        this.productPurchases = productPurchases;
    }

    public boolean addPurchase(ProductPurchase productPurchase) {
        return productPurchases.add(productPurchase);
    }

    public double getSubTotal() {
        double subTotal = 0;
        for (ProductPurchase productPurchase : productPurchases) {
            subTotal += productPurchase.getProduct().getPrice();
        }
        return subTotal;
    }
}
