package sqa.hanu_minimart.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderLine")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(6) UNSIGNED", updatable = false)
    private Long orderLineID;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity",columnDefinition = "int(10) not null")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Transient
    private double price;

    public OrderLine(Order order, int quantity, Product product, double price) {
        this.order = order;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
    }

    public OrderLine() {

    }

    public Long getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(Long orderLineID) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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
