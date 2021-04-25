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
import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.repository.CartRepository;

@ContextConfiguration(classes = {CartService.class})
@ExtendWith(SpringExtension.class)
public class CartServiceTest {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Test
    public void testGetAllCart() {
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        when(this.cartRepository.findAll()).thenReturn(cartList);
        List<Cart> actualAllCart = this.cartService.getAllCart();
        assertSame(cartList, actualAllCart);
        assertTrue(actualAllCart.isEmpty());
        verify(this.cartRepository).findAll();
    }

    @Test
    public void testDeleteCart() {
        doNothing().when(this.cartRepository).deleteById((Long) any());
        when(this.cartRepository.existsById((Long) any())).thenReturn(true);
        this.cartService.deleteCart(123L);
        verify(this.cartRepository).deleteById((Long) any());
        verify(this.cartRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteCart2() {
        doNothing().when(this.cartRepository).deleteById((Long) any());
        when(this.cartRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.cartService.deleteCart(123L));
        verify(this.cartRepository).existsById((Long) any());
    }
}

