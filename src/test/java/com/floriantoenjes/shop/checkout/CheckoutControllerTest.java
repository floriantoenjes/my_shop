package com.floriantoenjes.shop.checkout;

import com.floriantoenjes.shop.user.Role;
import com.floriantoenjes.shop.user.User;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Check;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CheckoutControllerTest {
    private MockMvc mockMvc;

    @Autowired
    CheckoutController checkoutController;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/templates/");
        viewResolver.setSuffix(".html");

        User user = new User("user", "password");
        user.setId(1L);
        user.setRole(new Role("ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));

        mockMvc = MockMvcBuilders.standaloneSetup(checkoutController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void checkout1WithoutAddressReturnsCheckout1Page() throws Exception {
        mockMvc.perform(get("/checkout/checkout1"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout1"));
    }

    @Test
    public void addAddress() throws Exception {

    }

    @Test
    public void checkout2() throws Exception {

    }

    @Test
    public void confirmPurchase() throws Exception {

    }

}