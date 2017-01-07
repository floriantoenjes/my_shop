package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PurchaseControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;


    @Autowired
    private Purchase purchase;

    @Autowired
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
        Product product = new Product("Knife", 3.0, 20L);
        productService.save(product);

        mockMvc.perform(post("/purchase/add")
                .param("id", "1")
                .param("quantity", "1")
        ).andExpect(redirectedUrl("/product/"));
        assertEquals(purchase.getProductPurchases().size(), 1);
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
    public void emptyCartTest() throws Exception {

    }

}