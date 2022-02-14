package shop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "transport")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Transport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String code;

	@Column(name = "cod_price")
	private double codPrice;

	@Column(name = "from_address")
	private String fromAddress;

	@Column(name = "to_address")
	private String toAddress;

	private Integer status;

	@Column(name = "cancle_at")
	private LocalDateTime cancleAt;

//	giao thanh cong luc
	@Column(name = "done_at")
	private LocalDateTime doneAt;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
	@ManyToOne
	@JoinColumn(name = "transporter_id")
	private Transporter transporter;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	

//	Constructor
	public Transport() {
		super();
	}

	public Transport(String code, double codPrice, String fromAddress, String toAddress) {
		super();
		this.code = code;
		this.codPrice = codPrice;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getCodPrice() {
		return codPrice;
	}

	public void setCodPrice(double codPrice) {
		this.codPrice = codPrice;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getCancleAt() {
		return cancleAt;
	}

	public void setCancleAt(LocalDateTime cancleAt) {
		this.cancleAt = cancleAt;
	}

	public LocalDateTime getDoneAt() {
		return doneAt;
	}

	public void setDoneAt(LocalDateTime doneAt) {
		this.doneAt = doneAt;
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

	public Transporter getTransporter() {
		return transporter;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

	
	// getter and setter

}
