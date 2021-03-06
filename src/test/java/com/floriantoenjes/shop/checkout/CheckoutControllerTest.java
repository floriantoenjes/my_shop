package com.floriantoenjes.shop.checkout;

import com.floriantoenjes.shop.address.Address;
import com.floriantoenjes.shop.user.Role;
import com.floriantoenjes.shop.user.User;
import com.floriantoenjes.shop.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CheckoutControllerTest {
    private MockMvc mockMvc;

    @Autowired
    CheckoutController checkoutController;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/templates/");
        viewResolver.setSuffix(".html");

        User user = new User("user", "password");
        user.setId(1L);
        user.setRole(new Role("ROLE_USER"));
        userService.save(user);
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
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void checkout1WithAddressRedirectsToCheckout2Page() throws Exception {
        User user = userService.findByUsername("user");
        Address address = new Address();
        address.setName("Test Address 1");
        user.setShippingAddresses(new ArrayList<>(Arrays.asList(address)));
        userService.save(user);

        mockMvc.perform(get("/checkout/checkout1"))
                .andExpect(redirectedUrl("/checkout/checkout2"));
    }

    @Test
    public void addAddressTest() throws Exception {
        mockMvc.perform(post("/checkout/checkout1"));
        // ToDo: Declare all the parameters for an address here.
    }

    @Test
    public void checkout2Test() throws Exception {
        mockMvc.perform(get("/checkout/checkout2"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout2"));
    }

    @Test
    public void confirmPurchase() throws Exception {

    }

}