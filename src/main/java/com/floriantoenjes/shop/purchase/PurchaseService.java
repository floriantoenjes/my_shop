package com.floriantoenjes.shop.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public List<Purchase> findAll() {
        return (List<Purchase>) purchaseRepository.findAll();
    }
}
