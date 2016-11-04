package com.floriantoenjes.shop.cart;

import com.floriantoenjes.shop.purchase.Purchase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Purchase> purchases;

    public Cart() {
        purchases = new ArrayList<>();
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public boolean addPurchase(Purchase purchase) {
        return purchases.add(purchase);
    }

    public double getSubTotal() {
        double subTotal = 0;
        for (Purchase purchase : purchases) {
            subTotal += purchase.getProduct().getPrice();
        }
        return subTotal;
    }
}
