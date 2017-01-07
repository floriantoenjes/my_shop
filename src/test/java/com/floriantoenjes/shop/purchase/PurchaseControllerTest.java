package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PurchaseControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private Purchase purchase;

    @InjectMocks
    private PurchaseController purchaseController;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(purchaseController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void addProductToCartTest() throws Exception {

    }

    @Test
    public void showCartTest() throws Exception {
        mockMvc.perform(get("/purchase/cart"))
                .andExpect(view().name("cart"));
    }

    @Test
    public void updateCart() throws Exception {

    }

    @Test
    public void emptyCart() throws Exception {

    }

}