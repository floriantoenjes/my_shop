package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void emptyCartTest() throws Exception {
        Product product1 = new Product("Knife", 3.0, 20L);
        product1.setId(1L);
        ProductPurchase productPurchase1 = new ProductPurchase(product1, 5L);
        List<ProductPurchase> productPurchases = new ArrayList<>(Arrays.asList(productPurchase1));
        int productPurchasesSizeBefore = productPurchases.size();
                when(purchase.getProductPurchases()).thenReturn(productPurchases);
        when(productService.findOne(1L)).thenReturn(product1);

        mockMvc.perform(get("/purchase/cart/empty"))
                .andExpect(redirectedUrl("/purchase/cart"));
        org.junit.Assert.assertNotEquals(productPurchasesSizeBefore, purchase.getProductPurchases().size());
    }

}