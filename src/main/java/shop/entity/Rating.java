package shop.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Rating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 @EmbeddedId
	 private UserProductKey id = new UserProductKey();

	 @ManyToOne(fetch = FetchType.EAGER)
	 @MapsId("userId")
	 @JoinColumn(name = "user_id")
	 private User user;
	 
	 
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;


	@Column
	private int star;

	@Column
	private String content;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

//	Constructor
	public Rating() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public UserProductKey getId() {
		return id;
	}

	public void setId(UserProductKey id) {
		this.id = id;
	}

	
	// getter and setter

}
