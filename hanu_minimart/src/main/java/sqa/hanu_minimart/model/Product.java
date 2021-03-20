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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "Product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "id", columnDefinition = "INT(6) UNSIGNED ", precision = 6, updatable = false)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(precision = 8, scale = 2)
    private double price;
    @Column(columnDefinition = "INT(6) UNSIGNED")
    private int quantity;
    @Column(length = 16)
    private String category;
    @Column(length = 3)
    private ProductStatus productStatus;
    @Column(name = "importDate", nullable = false, updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime importDate;
    private LocalDate expireDate;
    @Lob
    private String status;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderLine> orderLine = new HashSet<>();
//    @OneToMany(mappedBy = "cart")
//    @JsonIgnore
//    private Set<CartItem> cartItem = new HashSet<>();
    
    
    public Product(String name, double price, int quantity, String category, LocalDate expireDate) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.expireDate = expireDate;
	}

    public Product(Long id, String name, double price, int quantity, String category, ProductStatus productStatus,
			LocalDateTime importDate, LocalDate expireDate, String status, Set<OrderLine> orderLine,
			Set<CartItem> cartItem) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.productStatus = productStatus;
		this.importDate = importDate;
		this.expireDate = expireDate;
		this.status = status;
		this.orderLine = orderLine;
	}

	public Product() {

    }

    public Product(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
}

