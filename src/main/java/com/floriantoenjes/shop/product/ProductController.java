package com.floriantoenjes.shop.product;

import com.floriantoenjes.shop.purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
@Scope("request")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    Purchase purchase;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("products", productService.findAll());
        model.addAttribute("subTotal", purchase.getSubTotal());
        return "products";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findOne(id));
        return "detail";
    }
}
