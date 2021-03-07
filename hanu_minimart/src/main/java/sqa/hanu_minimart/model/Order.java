package sqa.hanu_minimart.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Order")
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "orderID")
    private int orderID;
    @Column(name = "customerID")
    private int customerID;
    @Column(name = "billing_address")
    private String billingAddress;
    @Column(name = "status")
    private OrderStatus status;

    public Order(int orderID, int customerID, String billingAddress, OrderStatus status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.billingAddress = billingAddress;
        this.status = status;
    }

    public Order() {

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customerID=" + customerID +
                ", billingAddress='" + billingAddress + '\'' +
                ", orderLineID=" +
                ", approved=" + status +
                '}';
    }
}
