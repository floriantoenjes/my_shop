package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import com.floriantoenjes.shop.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("purchase")
@Scope("request")
public class PurchaseController {

    @Autowired
    Purchase purchase;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(@RequestParam("id") Long productId, @RequestParam("quantity") Long quantity,
                             RedirectAttributes redirectAttributes) {
        Product product = productService.findOne(productId);
        boolean alreadyInPurchase = false;

        Long stockQuantity = product.getStockQuantity();
        if (stockQuantity < quantity) {
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("You can't add more products than are in stock",
                            FlashMessage.Status.FAILED));
            return "redirect:/product/";
        }

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

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productService.save(product);

        return "redirect:/product/";
    }

    @RequestMapping("cart")
    public String showCart(Model model) {
        model.addAttribute("ppurchases", purchase.getProductPurchases());
        model.addAttribute("subTotal", purchase.getSubTotal());
        return "cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateCart(@RequestParam("productId") Long productId, @RequestParam("newQuantity") Long newQuantity) {
        Product product = productService.findOne(productId);

        for (ProductPurchase productPurchase : purchase.getProductPurchases()) {
            if (productPurchase.getProduct().getId() == product.getId()) {
                Long oldPurchaseQuantity = productPurchase.getQuantity();
                if (product.getStockQuantity() + oldPurchaseQuantity < newQuantity) {

                } else {
                    productPurchase.setQuantity(newQuantity);
                    product.setStockQuantity((product.getStockQuantity() + oldPurchaseQuantity) - newQuantity );
                    productService.save(product);
                }
            }
        }
        return "redirect:/purchase/cart";
    }

}
