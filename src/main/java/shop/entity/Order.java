package shop.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name_receiver")
	private String nameReceiver;

	@Column(name = "phone_receiver")
	private String phoneReceiver;

	@Column(name = "address_receiver")
	private String addressReceiver;

	@Column
	private double total;

	@Column
	private double discount;

	@Column(name = "shipping_fee")
	private double shippingFee;

	@Column
	private String note;

	@Column(name = "confirm_at")
	private LocalDateTime confirmAt;

	@Column(name = "status")
	private Integer status;

	@Column(name = "pay_at")
	private LocalDateTime payAt;

	@Column(name = "cancle_at")
	private LocalDateTime cancleAt;

	@Column(name = "cancle_reason")
	private String cancleReason;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "voucher_id", nullable = false)
	private Voucher voucher;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "payment_id", nullable = false)
	private Payment payment;

	
	@JsonIgnore
	@OneToOne(mappedBy = "order")
	private Transport transport;

	
//	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderDetail> orderDetails;

//	Constructor
	public Order() {
		super();
	}

	public Order(String nameReceiver, String phoneReceiver, String addressReceiver, double total, double discount,
			double shippingFee, String note) {
		super();
		this.nameReceiver = nameReceiver;
		this.phoneReceiver = phoneReceiver;
		this.addressReceiver = addressReceiver;
		this.total = total;
		this.discount = discount;
		this.shippingFee = shippingFee;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameReceiver() {
		return nameReceiver;
	}

	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}

	public String getPhoneReceiver() {
		return phoneReceiver;
	}

	public void setPhoneReceiver(String phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}

	public String getAddressReceiver() {
		return addressReceiver;
	}

	public void setAddressReceiver(String addressReceiver) {
		this.addressReceiver = addressReceiver;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getConfirmAt() {
		return confirmAt;
	}

	public void setConfirmAt(LocalDateTime confirmAt) {
		this.confirmAt = confirmAt;
	}

	

	public LocalDateTime getPayAt() {
		return payAt;
	}

	public void setPayAt(LocalDateTime payAt) {
		this.payAt = payAt;
	}

	public LocalDateTime getCancleAt() {
		return cancleAt;
	}

	public void setCancleAt(LocalDateTime cancleAt) {
		this.cancleAt = cancleAt;
	}

	public String getCancleReason() {
		return cancleReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
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

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	// getter and setter

}
