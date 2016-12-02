package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.address.Address;
import com.floriantoenjes.shop.core.BaseEntity;
import com.floriantoenjes.shop.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Scope("session")
public class Purchase extends BaseEntity {
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<ProductPurchase> productPurchases;

    @ManyToOne
    private User user;

    @ManyToOne
    private Address shippingAddress;

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
        productPurchase.setPurchase(this);
        return productPurchases.add(productPurchase);
    }

    public User getUser() {
        return user;
    }

    public boolean setUser(User user) {
        this.user = user;
        return user.addPurchase(this);
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getSubTotal() {
        double subTotal = 0;
        for (ProductPurchase productPurchase : productPurchases) {
            subTotal += productPurchase.getProduct().getPrice() * productPurchase.getQuantity();
        }
        return String.format("%.2f", subTotal);
    }
}
