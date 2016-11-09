package com.floriantoenjes.shop.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/product/";
    }
}
