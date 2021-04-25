package sqa.hanu_minimart.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.repository.OrderLineRepository;

@ContextConfiguration(classes = {OrderLineService.class})
@ExtendWith(SpringExtension.class)
public class OrderLineServiceTest {
    @MockBean
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderLineService orderLineService;

    @Test
    public void testGetById() {
        when(this.orderLineRepository.findById((Long) any())).thenReturn(Optional.<OrderLine>empty());
        this.orderLineService.getById(123L);
        verify(this.orderLineRepository).findById((Long) any());
    }

    @Test
    public void testGetAllOrderLine() {
        ArrayList<OrderLine> orderLineList = new ArrayList<OrderLine>();
        when(this.orderLineRepository.findAll()).thenReturn(orderLineList);
        List<OrderLine> actualAllOrderLine = this.orderLineService.getAllOrderLine();
        assertSame(orderLineList, actualAllOrderLine);
        assertTrue(actualAllOrderLine.isEmpty());
        verify(this.orderLineRepository).findAll();
    }

    @Test
    public void testDeleteAll() {
        doNothing().when(this.orderLineRepository).deleteAll();
        this.orderLineService.deleteAll();
        verify(this.orderLineRepository).deleteAll();
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.orderLineRepository).deleteById((Long) any());
        this.orderLineService.deleteById(123L);
        verify(this.orderLineRepository).deleteById((Long) any());
    }

    @Test
    public void testUpdate() {
        when(this.orderLineRepository.findById((Long) any())).thenReturn(Optional.<OrderLine>empty());
        when(this.orderLineRepository.existsById((Long) any())).thenReturn(true);
        this.orderLineService.update(new OrderLine(), 123L);
        verify(this.orderLineRepository).existsById((Long) any());
        verify(this.orderLineRepository).findById((Long) any());
    }
}

