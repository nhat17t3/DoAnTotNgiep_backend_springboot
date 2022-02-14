package shop.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderProductKey implements Serializable {


//	@Column(name = "order_id")
	private int orderId;

//	@Column(name = "product_id")
	private int productId;
	
	

	public OrderProductKey() {
	super();
}

	public OrderProductKey(int orderId, int productId) {
	super();
	this.orderId = orderId;
	this.productId = productId;
}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
		result = prime * result + productId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProductKey other = (OrderProductKey) obj;
		if (orderId != other.orderId)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	
	
	
}
