package sqa.hanu_minimart.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.repository.OrderRepository;

@ContextConfiguration(classes = {OrderService.class, OrderLineService.class, ProductService.class, CartService.class,
        CartItemService.class, AccountService.class})
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private CartItemService cartItemService;

    @MockBean
    private CartService cartService;

    @MockBean
    private OrderLineService orderLineService;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllOrders() {
        ArrayList<Order> orderList = new ArrayList<Order>();
        when(this.orderRepository.findAllOrder()).thenReturn(orderList);
        List<Order> actualAllOrders = this.orderService.getAllOrders();
        assertSame(orderList, actualAllOrders);
        assertTrue(actualAllOrders.isEmpty());
        verify(this.orderRepository).findAllOrder();
    }

    @Test
    public void testGetOrderByUserId() {
        ArrayList<Order> orderList = new ArrayList<Order>();
        when(this.orderRepository.findByUser_Id((Long) any())).thenReturn(orderList);
        List<Order> actualOrderByUserId = this.orderService.getOrderByUserId(123L);
        assertSame(orderList, actualOrderByUserId);
        assertTrue(actualOrderByUserId.isEmpty());
        verify(this.orderRepository).findByUser_Id((Long) any());
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(this.orderRepository).deleteById((Long) any());
        when(this.orderRepository.existsById((Long) any())).thenReturn(true);
        this.orderService.deleteOrder(1L);
        verify(this.orderRepository).deleteById((Long) any());
        verify(this.orderRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteOrder2() {
        doNothing().when(this.orderRepository).deleteById((Long) any());
        when(this.orderRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.orderService.deleteOrder(1L));
        verify(this.orderRepository).existsById((Long) any());
    }
}

