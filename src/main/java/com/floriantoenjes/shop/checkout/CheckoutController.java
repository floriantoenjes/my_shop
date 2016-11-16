package com.floriantoenjes.shop.checkout;

import com.floriantoenjes.shop.address.Address;
import com.floriantoenjes.shop.purchase.Purchase;
import com.floriantoenjes.shop.user.User;
import com.floriantoenjes.shop.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("checkout")
@Scope("request")
public class CheckoutController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Purchase purchase;

    @RequestMapping("checkout1")
    public String checkout1(Model model) {
        model.addAttribute("address", new Address());
        return "checkout1";
    }

    @RequestMapping(value = "checkout2", method = RequestMethod.POST)
    public String addAddress(Address address) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        user.setShippingAddress(address);
        userRepository.save(user);
        return "redirect:/checkout/checkout2";
    }

    @RequestMapping("checkout2")
    public String checkout2(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        model.addAttribute("ppurchases", purchase.getProductPurchases());
        model.addAttribute("subTotal", purchase.getSubTotal());

        model.addAttribute("sAddress", user.getShippingAddress());

        return "checkout2";
    }

}
