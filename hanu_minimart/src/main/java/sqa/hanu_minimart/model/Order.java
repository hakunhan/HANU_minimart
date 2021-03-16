package sqa.hanu_minimart.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "order")
	private Set<OrderLine> orderLine = new HashSet<>();
	@Lob
	private String deliveryNotes;
	@Lob
	private String deliveryTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	@Lob
	private String status;
	private float total;
	public Order() {

	}

	public Order(int id, User user, String deliveryNotes, String deliveryTime, Date createdTime,String status, float total) {
		super();
		this.id = id;
		this.user = user;
		this.deliveryNotes = deliveryNotes;
		this.deliveryTime = deliveryTime;
		this.createdTime = createdTime;
		this.status = status;
		this.total = total;
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

	public String getDeliveryNotes() {
		return deliveryNotes;
	}

	public void setDeliveryNotes(String deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
