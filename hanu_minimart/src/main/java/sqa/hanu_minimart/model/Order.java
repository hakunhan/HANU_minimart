package sqa.hanu_minimart.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(6) UNSIGNED ", precision = 6, updatable = false)
    private int id;
    @ManyToOne(optional = true)
    private User user;
    @OneToMany
    private Set<OrderLine> orderLine = new HashSet<>();
    @Lob
    private String deliveryNotes;
    private LocalDate deliverTime;
    @Column(name = "billing_address")
    private String billingAddress;
    @Column(name = "status")
    private OrderStatus status;
    @Transient
    private Double total;

    public Order(int id, User user, String deliveryNotes, String billingAddress, OrderStatus status) {
        this.id = id;
        this.user = user;
        this.deliveryNotes = deliveryNotes;
        this.billingAddress = billingAddress;
        this.status = status;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(Set<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public LocalDate getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(LocalDate deliverTime) {
        this.deliverTime = deliverTime;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderLine=" + orderLine +
                ", deliveryNotes='" + deliveryNotes + '\'' +
                ", deliverTime=" + deliverTime +
                ", billingAddress='" + billingAddress + '\'' +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
