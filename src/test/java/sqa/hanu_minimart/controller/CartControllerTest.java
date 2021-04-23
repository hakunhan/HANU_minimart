package sqa.hanu_minimart.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.hanu_minimart.service.CartService;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
public class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartService cartService;

    @Test
    public void testDeleteCart() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetByUser() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/getByUser");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetOne() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

