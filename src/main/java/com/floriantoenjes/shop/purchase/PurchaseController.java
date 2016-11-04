package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("purchase")
@Scope("request")
public class PurchaseController {

    @Autowired
    Purchase purchase;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(@RequestParam("id") Long productId, @RequestParam("quantity") Long quantity) {
        Product product = productService.findOne(productId);
        ProductPurchase productPurchase = new ProductPurchase(product, quantity);

        purchase.addPurchase(productPurchase);

        return "redirect:/product/";
    }
}
