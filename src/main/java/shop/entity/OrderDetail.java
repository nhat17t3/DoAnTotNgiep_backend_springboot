package shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_detail")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderProductKey id  = new OrderProductKey();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

//	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;

	@Column
	private int quantity;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "promotion_price")
	private double promotionPrice;

//	Constructor
	public OrderDetail() {
		super();
	}
	
	

	public OrderDetail(OrderProductKey id, Order order, Product product, int quantity, double unitPrice,
		double promotionPrice) {
	super();
	this.id = id;
	this.order = order;
	this.product = product;
	this.quantity = quantity;
	this.unitPrice = unitPrice;
	this.promotionPrice = promotionPrice;
}



	// getter and setter
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public OrderProductKey getId() {
		return id;
	}

	public void setId(OrderProductKey id) {
		this.id = id;
	}

}
