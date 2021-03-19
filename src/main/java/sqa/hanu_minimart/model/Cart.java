package sqa.hanu_minimart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional = true)
	private User user;

	@OneToMany(mappedBy = "cart")
	@JsonIgnore
	private Set<CartItem> cartItem = new HashSet<>();

	@Column(nullable = false, updatable = false, insertable = false,
			columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	private LocalDateTime importDate;
	@Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updateAt;

	@Lob
	private String content;
	
	public Cart() {
		super();
	}
	
	public Cart(Long id, User user, String content) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(Set<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
}
