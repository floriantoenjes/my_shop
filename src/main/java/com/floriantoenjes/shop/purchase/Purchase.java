package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.core.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Scope("session")
public class Purchase extends BaseEntity {
    @OneToMany(mappedBy = "purchase")
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

    public boolean addProductPurchase(ProductPurchase productPurchase) {
        return productPurchases.add(productPurchase);
    }

    public String getSubTotal() {
        double subTotal = 0;
        for (ProductPurchase productPurchase : productPurchases) {
            subTotal += productPurchase.getProduct().getPrice() * productPurchase.getQuantity();
        }
        return String.format("%.2f", subTotal);
    }
}
