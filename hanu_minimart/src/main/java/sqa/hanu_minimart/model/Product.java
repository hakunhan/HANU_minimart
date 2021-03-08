package sqa.hanu_minimart.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    @Lob
    private String status;
    @OneToMany
    private Set<OrderLine> orderLine = new HashSet<>();
    @OneToMany
    private Set<CartItem> cartItem = new HashSet<>();
    
    
    public Product(String name, double price, int quantity, String category, LocalDate expireDate) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.expireDate = expireDate;
	}

	public Product(String name, double price, int quantity, String category,
                   LocalDate expireDate, String status) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.expireDate = expireDate;
        this.status = status;
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

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", importDate=" + importDate +
                ", expireDate=" + expireDate +
                ", status=" + status +
                '}';
=======
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
>>>>>>> origin/main
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

<<<<<<< HEAD
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<OrderLine> getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(Set<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}

	public Set<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	
=======
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

>>>>>>> origin/main

}
