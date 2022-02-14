package shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserProductKey implements Serializable {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

//	@Column(name = "user_id")
	private int userId;

//	@Column(name = "product_id")
	private int productId;

	public UserProductKey() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public UserProductKey(int userId, int productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + userId;
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
		UserProductKey other = (UserProductKey) obj;
		if (productId != other.productId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
