package sqa.hanu_minimart.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "Product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "id", columnDefinition = "INT(6) UNSIGNED ", precision = 6, updatable = false)
    private int id;
    @Column(length = 50)
    private String name;
    @Column(precision = 8, scale = 2)
    private double price;
    @Column(columnDefinition = "INT(6) UNSIGNED")
    private int quantity;
    @Column(length = 16)
    private String category;
    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private ProductStatus productStatus;
    @Column(name = "importDate", nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime importDate;
    private LocalDate expireDate;

    public Product(String name, double price, int quantity, String category,
                   LocalDate expireDate) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.expireDate = expireDate;
    }

    public Product() {

    }

    public Product(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public LocalDateTime getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDateTime importDate) {
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
                ", category='" + category + '\'' +
                ", productStatus=" + productStatus +
                ", importDate=" + importDate +
                ", expireDate=" + expireDate +
                '}';
    }


}
