package sqa.hanu_minimart.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderLine")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLineID", columnDefinition = "int(6) UNSIGNED", updatable = false)
    private int orderLineID;
    @ManyToOne
    private Order order;
    @Column(name = "quantity",columnDefinition = "int(10) not null")
    private int quantity;
    @Column(name = "product",length = 30)
    private String product;
    @Transient
    private double price;

    public OrderLine(int orderLineID, Order order, int quantity, String product, double price) {
        this.orderLineID = orderLineID;
        this.order = order;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
    }

    public OrderLine() {

    }

    public int getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(int orderLineID) {
        this.orderLineID = orderLineID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineID=" + orderLineID +
                ", order=" + order +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                ", price=" + price +
                '}';
    }
}
