package com.floriantoenjes.shop.product;

import com.floriantoenjes.shop.purchase.Purchase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private Purchase purchase;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void viewProductsTest() throws Exception {
        List<Product> products = new ArrayList<>(Arrays.asList(new Product("Fork", 10.0, 3L)));
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/"))
                .andExpect(view().name("products"))
                .andExpect(model().attribute("products", equalTo(products)));
    }

    @Test
    public void viewProductDetailsTest() throws Exception {
        Product product = new Product("Spoon", 15.0, 5L);
        when(productService.findOne(1L)).thenReturn(product);

        mockMvc.perform(get("/product/detail/1"))
                .andExpect(view().name("detail"))
                .andExpect(model().attribute("product", equalTo(product)));
    }


}