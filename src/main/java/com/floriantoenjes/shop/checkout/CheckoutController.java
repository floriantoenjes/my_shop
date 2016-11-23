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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user.getShippingAddresses().size() == 0) {
            model.addAttribute("address", new Address());
            return "checkout1";
        } else {
            return "redirect:/checkout/checkout2";
        }
    }

    @RequestMapping(value = "checkout2", method = RequestMethod.POST)
    public String addAddress(Address address) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
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

        model.addAttribute("sAddress", user.getShippingAddresses());

        return "checkout2";
    }

}
