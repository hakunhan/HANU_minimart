package sqa.hanu_minimart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.payload.OrderPayload;
import sqa.hanu_minimart.repository.CartItemRepository;
import sqa.hanu_minimart.repository.CartRepository;
import sqa.hanu_minimart.repository.OrderLineRepository;
import sqa.hanu_minimart.repository.OrderRepository;
import sqa.hanu_minimart.repository.ProductRepository;
import sqa.hanu_minimart.service.AccountService;
import sqa.hanu_minimart.service.CartItemService;
import sqa.hanu_minimart.service.CartService;
import sqa.hanu_minimart.service.OrderLineService;
import sqa.hanu_minimart.service.OrderService;
import sqa.hanu_minimart.service.ProductService;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    @Test
    public void testDeleteOrder() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{orderID}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateOrder() {
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderLineService orderLineService = new OrderLineService(mock(OrderLineRepository.class));
        AccountService accountService = new AccountService();
        CartItemRepository cartItemRepository = mock(CartItemRepository.class);
        CartRepository cartRepository = mock(CartRepository.class);
        CartItemService cartItemSevice = new CartItemService(cartItemRepository, cartRepository,
                new ProductService(mock(ProductRepository.class), mock(CartItemRepository.class), null));
        OrderController orderController = new OrderController(new OrderService(orderRepository, orderLineService,
                accountService, cartItemSevice, new CartService(mock(CartRepository.class))));
        ResponseEntity<?> actualUpdateOrderResult = orderController.updateOrder(new Order(), 123L);
        assertNull(actualUpdateOrderResult.getBody());
        assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,[]>", actualUpdateOrderResult.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateOrderResult.getStatusCode());
    }

    @Test
    public void testUpdateOrder2() {
        OrderController orderController = new OrderController(null);
        ResponseEntity<?> actualUpdateOrderResult = orderController.updateOrder(new Order(), 123L);
        assertNull(actualUpdateOrderResult.getBody());
        assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,[]>", actualUpdateOrderResult.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateOrderResult.getStatusCode());
    }

    @Test
    public void testGetAllOrder() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetByUserId() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/getByUserId");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userId", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testNewOrder() throws Exception {
        OrderPayload orderPayload = new OrderPayload();
        orderPayload.setUserId(123L);
        orderPayload.setCartId(123L);
        String content = (new ObjectMapper()).writeValueAsString(orderPayload);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

