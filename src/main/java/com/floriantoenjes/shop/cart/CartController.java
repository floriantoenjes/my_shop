package com.floriantoenjes.shop.cart;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import com.floriantoenjes.shop.purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    Cart cart;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(@RequestParam("id") Long productId, @RequestParam("quantity") Long quantity) {
        Product product = productService.findOne(productId);
        Purchase purchase = new Purchase(product, quantity);

        cart.addPurchase(purchase);

        return "redirect:/product/";
    }
}
