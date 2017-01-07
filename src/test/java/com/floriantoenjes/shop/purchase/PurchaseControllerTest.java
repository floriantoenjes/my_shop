package com.floriantoenjes.shop.purchase;

import com.floriantoenjes.shop.product.Product;
import com.floriantoenjes.shop.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

    // ToDo: Add tests to ensure correct stock quantity

    @Test
    public void addProductToCartTest() throws Exception {
        Product product = mockProduct();
        productService.save(product);

        mockMvc.perform(post("/purchase/add")
                .param("id", "1")
                .param("quantity", "1"))
                .andExpect(redirectedUrl("/product/"));

        assertEquals(purchase.getProductPurchases().size(), 1);
    }

    @Test
    public void showCartTest() throws Exception {
        mockMvc.perform(get("/purchase/cart"))
                .andExpect(view().name("cart"));
    }

    @Test
    public void updateCartTest() throws Exception {
        Product product = mockProduct();
        productService.save(product);
        Long quantityBefore = 3L;
        purchase.addProductPurchase(new ProductPurchase(product, quantityBefore));

        mockMvc.perform(post("/purchase/update")
                .param("productId", "1")
                .param("newQuantity", "10"))
                .andExpect(redirectedUrl("/purchase/cart"));

        assertEquals(java.util.Optional.of(10L).get(),
                purchase.getProductPurchases().get(0).getQuantity());
    }

    @Test
    public void emptyCartTest() throws Exception {
        purchase.addProductPurchase(new ProductPurchase(mockProduct(), 5L));
        int sizeBefore = purchase.getProductPurchases().size();

        mockMvc.perform(get("/purchase/cart/empty"))
                .andExpect(redirectedUrl("/purchase/cart"));
        assertNotEquals(sizeBefore, purchase.getProductPurchases().size());
    }

    private Product mockProduct() {
        Product product = new Product("Knife", 3.0, 20L);
        product.setId(1L);
        return product;
    }

}