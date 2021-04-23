package sqa.hanu_minimart.service;

import static org.junit.jupiter.api.Assertions.assertSame;
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
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.repository.CartItemRepository;
import sqa.hanu_minimart.repository.CartRepository;

@ContextConfiguration(classes = {ProductService.class, CartItemService.class})
@ExtendWith(SpringExtension.class)
public class CartItemServiceTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllItem() {
        ArrayList<CartItem> cartItemList = new ArrayList<CartItem>();
        when(this.cartItemRepository.findAll()).thenReturn(cartItemList);
        List<CartItem> actualAllItem = this.cartItemService.getAllItem();
        assertSame(cartItemList, actualAllItem);
        assertTrue(actualAllItem.isEmpty());
        verify(this.cartItemRepository).findAll();
    }

    @Test
    public void testDeleteAll() {
        doNothing().when(this.cartItemRepository).deleteAll();
        this.cartItemService.deleteAll();
        verify(this.cartItemRepository).deleteAll();
    }

    @Test
    public void testDeleteByCartId() {
        doNothing().when(this.cartItemRepository).deleteAllByCart_Id((Long) any());
        this.cartItemService.deleteByCartId(123L);
        verify(this.cartItemRepository).deleteAllByCart_Id((Long) any());
    }
}

