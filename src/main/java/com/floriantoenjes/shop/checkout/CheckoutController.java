package com.floriantoenjes.shop.checkout;

import com.floriantoenjes.shop.address.Address;
import com.floriantoenjes.shop.purchase.Purchase;
import com.floriantoenjes.shop.purchase.PurchaseService;
import com.floriantoenjes.shop.user.User;
import com.floriantoenjes.shop.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Controller
@RequestMapping("checkout")
@Scope("request")
@Transactional
public class CheckoutController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    Purchase purchase;

    @RequestMapping("checkout1")
    public String checkout1(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user.getShippingAddresses().size() == 0) {
            model.addAttribute("address", new Address());
            return "checkout1";
        } else {
            return "redirect:/checkout/checkout2";
        }
    }

    @RequestMapping(value = "checkout1", method = RequestMethod.POST)
    public String addAddress(Address address) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user.getShippingAddresses().size() == 0) {
            address.setDefaultAddress(true);
            user.setBillingAddress(address);
        }
        user.addShippingAddress(address);
        userRepository.save(user);
        return "redirect:/checkout/checkout2";
    }

    @RequestMapping("checkout2")
    public String checkout2(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        model.addAttribute("ppurchases", purchase.getProductPurchases());
        model.addAttribute("subTotal", purchase.getSubTotal());

        model.addAttribute("sAddresses", user.getShippingAddresses());
        model.addAttribute("shippingAddress", new Address());


        return "checkout2";
    }

    @RequestMapping(value = "checkout2", method = RequestMethod.POST)
    public String confirmPurchase(Address shippingAddress) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        purchase.setUser(user);
        purchase.setShippingAddress(shippingAddress);

        purchaseService.save(purchase);

        purchase.setProductPurchases(new ArrayList<>());

        return "redirect:/";
    }

}
