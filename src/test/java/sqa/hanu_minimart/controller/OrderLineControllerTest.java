package sqa.hanu_minimart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.repository.OrderLineRepository;
import sqa.hanu_minimart.service.OrderLineService;

@ContextConfiguration(classes = {OrderLineController.class})
@ExtendWith(SpringExtension.class)
public class OrderLineControllerTest {
    @Autowired
    private OrderLineController orderLineController;

    @MockBean
    private OrderLineService orderLineService;

    @Test
    public void testCreateNewOrderLine() {
        OrderLineController orderLineController = new OrderLineController(
                new OrderLineService(mock(OrderLineRepository.class)));
        ResponseEntity<?> actualCreateNewOrderLineResult = orderLineController.createNewOrderLine(new OrderLine());
        assertNull(actualCreateNewOrderLineResult.getBody());
        assertEquals("<200 OK OK,[]>", actualCreateNewOrderLineResult.toString());
        assertEquals(HttpStatus.OK, actualCreateNewOrderLineResult.getStatusCode());
    }

    @Test
    public void testCreateNewOrderLine2() {
        OrderLineController orderLineController = new OrderLineController(null);
        ResponseEntity<?> actualCreateNewOrderLineResult = orderLineController.createNewOrderLine(new OrderLine());
        assertNull(actualCreateNewOrderLineResult.getBody());
        assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,[]>", actualCreateNewOrderLineResult.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualCreateNewOrderLineResult.getStatusCode());
    }

    @Test
    public void testUpdate() {
        OrderLineController orderLineController = new OrderLineController(
                new OrderLineService(mock(OrderLineRepository.class)));
        ResponseEntity<?> actualUpdateResult = orderLineController.update(new OrderLine(), 123L);
        assertNull(actualUpdateResult.getBody());
        assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,[]>", actualUpdateResult.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateResult.getStatusCode());
    }

    @Test
    public void testUpdate2() {
        OrderLineController orderLineController = new OrderLineController(null);
        ResponseEntity<?> actualUpdateResult = orderLineController.update(new OrderLine(), 123L);
        assertNull(actualUpdateResult.getBody());
        assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,[]>", actualUpdateResult.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUpdateResult.getStatusCode());
    }

    @Test
    public void testDeleteAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderLineController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderLineController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetAllOrderLine() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderLineController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderLineController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

