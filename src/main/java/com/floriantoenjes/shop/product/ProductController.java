package com.floriantoenjes.shop.product;

import com.floriantoenjes.shop.cart.Cart;
import com.floriantoenjes.shop.purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    Cart cart;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("products", productService.findAll());
        model.addAttribute("subTotal", cart.getSubTotal());
        return "products";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        return "detail";
    }
}
