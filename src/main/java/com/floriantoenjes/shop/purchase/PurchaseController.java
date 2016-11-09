package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        boolean alreadyInPurchase = false;

        for (ProductPurchase pp : purchase.getProductPurchases()) {
            if (product.getName().equals(pp.getProduct().getName())) {
                pp.setQuantity(pp.getQuantity() + quantity);
                alreadyInPurchase = true;
            }
        }

        if (!alreadyInPurchase) {
            ProductPurchase productPurchase = new ProductPurchase(product, quantity);
            purchase.addProductPurchase(productPurchase);
        }

        return "redirect:/product/";
    }

    @RequestMapping("cart")
    public String showCart(Model model) {
        model.addAttribute("ppurchases", purchase.getProductPurchases());
        model.addAttribute("subTotal", purchase.getSubTotal());
        return "cart";
    }
}
