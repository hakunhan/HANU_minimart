package sqa.hanu_minimart.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_line")
public class OrderLine {
    @Id
    @SequenceGenerator(name = "orderLine_sequence", sequenceName = "orderLine_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderLine_sequence")
    @Column(name = "order_lineID", columnDefinition = "int(10) primary key", precision = 10, updatable = false)
    private int orderLineID;
    @Column(name = "orderID",columnDefinition = "int(10) not null")
    private int orderID;
    @Column(name = "quantity",columnDefinition = "int(10) not null")
    private int quantity;
    @Column(name = "product",length = 30)
    private String product;
    @Column(name = "price",columnDefinition = "double(10) not null")
    private double price;

    public OrderLine(int orderLineID, int orderID, int quantity, String product, double price) {
        this.orderLineID = orderLineID;
        this.orderID = orderID;
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

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
                ", orderID=" + orderID +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                ", price=" + price +
                '}';
    }
}
