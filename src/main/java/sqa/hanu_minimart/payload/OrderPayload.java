package sqa.hanu_minimart.payload;

import org.springframework.data.annotation.CreatedDate;
import sqa.hanu_minimart.model.OrderStatus;
import sqa.hanu_minimart.model.User;

import javax.persistence.*;
import java.util.Date;

public class OrderPayload {
    private Long cartId;

    private Long userId;

    public OrderPayload(){

    }

    public OrderPayload(Long cartId, Long userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
