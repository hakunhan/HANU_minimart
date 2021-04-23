package sqa.hanu_minimart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.hanu_minimart.payload.CartItemPayLoad;
import sqa.hanu_minimart.service.CartItemService;

@ContextConfiguration(classes = {CartItemController.class})
@ExtendWith(SpringExtension.class)
public class CartItemControllerTest {
    @Autowired
    private CartItemController cartItemController;

    @MockBean
    private CartItemService cartItemService;

    @Test
    public void testAddNewItem() throws Exception {
        CartItemPayLoad cartItemPayLoad = new CartItemPayLoad();
        cartItemPayLoad.setQuantity(1);
        cartItemPayLoad.setProductName("Product Name");
        cartItemPayLoad.setContent("Not all who wander are lost");
        cartItemPayLoad.setCartId(123L);
        String content = (new ObjectMapper()).writeValueAsString(cartItemPayLoad);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartItemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteAllCartItem() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartItemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteCartItem() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartItemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartItemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartItemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateCartItem() throws Exception {
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/update/{id}", 1L);
        MockHttpServletRequestBuilder requestBuilder = putResult.param("quantity", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartItemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

