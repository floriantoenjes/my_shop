package com.floriantoenjes.shop.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedirectControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RedirectController redirectController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(redirectController).build();
    }

    @Test
    public void redirectToIndexTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/"));
    }

}