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
@Table(name = "favourite")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Favourite implements Serializable {

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

	@Column(name = "date_favourite")
	private LocalDateTime dateFavourite;

//	Constructor
	public Favourite() {
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

	public LocalDateTime getDateFavourite() {
		return dateFavourite;
	}

	public void setDateFavourite(LocalDateTime dateFavourite) {
		this.dateFavourite = dateFavourite;
	}

	public UserProductKey getId() {
		return id;
	}

	public void setId(UserProductKey id) {
		this.id = id;
	}

	// getter and setter

}
