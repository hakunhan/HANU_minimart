package sqa.hanu_minimart.payload;

import org.springframework.data.annotation.CreatedDate;
import sqa.hanu_minimart.model.OrderStatus;
import sqa.hanu_minimart.model.User;

import javax.persistence.*;
import java.util.Date;

public class OrderPayload {
    private Long cartId;

    private Long userId;

    @Lob
    private String deliveryNotes;

    @Column(name = "billing_address")
    private String billingAddress;

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

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
