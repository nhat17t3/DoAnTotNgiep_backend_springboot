package shop.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "voucher")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String code;

	@Column
	private String name;

	@Column
	private String type;

//	gia don hang toi thieu
	@Column(name = "min_order_value")
	private double minOrderValue;

//	muc giam gia toi da
	@Column(name = "max_price")
	private double maxPrice;

//	gia tri giam
	@Column
	private double value;

	@Column
	private int quantity;

	@Column(name = "start_at")
	private LocalDateTime startAt;

	@Column(name = "end_at")
	private LocalDateTime endAt;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "voucher" )
	private Set<Order> orders;

//	Constructor
	public Voucher() {
		super();
	}

	public Voucher(String code, String name, String type, double minOrderValue, double maxPrice, double value,
			int quantity, LocalDateTime startAt, LocalDateTime endAt, Boolean isActive, LocalDateTime createdAt) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
		this.minOrderValue = minOrderValue;
		this.maxPrice = maxPrice;
		this.value = value;
		this.quantity = quantity;
		this.startAt = startAt;
		this.endAt = endAt;
		this.isActive = isActive;
		this.createdAt = createdAt;
		
	}

	// getter and setter
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime time) {
		this.updatedAt = time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMinOrderValue() {
		return minOrderValue;
	}

	public void setMinOrderValue(double minOrderValue) {
		this.minOrderValue = minOrderValue;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public LocalDateTime getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	

}
