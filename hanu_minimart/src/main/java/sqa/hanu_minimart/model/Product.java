package sqa.hanu_minimart.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "Product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "id", columnDefinition = "UNSIGNED INT(6)", updatable = false)
    private int id;
    private String name;
    private double price;
    @Column(columnDefinition = "UNSIGNED INT(6)")
    private int quantity;
    private LocalDate importDate;
    private LocalDate expireDate;

    public Product(int id, String name, double price, int quantity, LocalDate importDate, LocalDate expireDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
        this.expireDate = expireDate;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", importDate=" + importDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
