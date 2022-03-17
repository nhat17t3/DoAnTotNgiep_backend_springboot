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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

//	@Column
//	private String slug;

	@Column
	private String code;

	@Column
	private String image;
	
	@Column(name = "more_image")
	private String moreImage;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "promotion_price")
	private double promotionPrice;

	@Column
	private int instock;

	@Column
	private int view;

	@Column(name = "short_desc")
	private String shortDesc;

	private String description;

	private String ingredient;

	private String specification;

	@Column(name = "is_hot")
	private Boolean isHot;

	@Column(name = "total_sale")
	private Integer totalSale;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
//	private Set<Category> categories;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderDetail> orderDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Favourite> favourites;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Rating> ratings;

//	Constructor
	public Product() {
		super();
	}

	public Product(String name, String code, double unitPrice, double promotionPrice, int instock,
			String shortDesc, String description, String ingredient, String specification, Boolean isHot,
			Boolean isActive) {
		super();
		this.name = name;
//		this.slug = slug;
		this.code = code;
		this.unitPrice = unitPrice;
		this.promotionPrice = promotionPrice;
		this.instock = instock;
		this.shortDesc = shortDesc;
		this.description = description;
		this.ingredient = ingredient;
		this.specification = specification;
		this.isHot = isHot;
		this.isActive = isActive;
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

//	public String getSlug() {
//		return slug;
//	}
//
//	public void setSlug(String slug) {
//		this.slug = slug;
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public int getInstock() {
		return instock;
	}

	public void setInstock(int instock) {
		this.instock = instock;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}



	public Integer getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(Integer totalSale) {
		this.totalSale = totalSale;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
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

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

//	public Set<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(Set<Category> categories) {
//		this.categories = categories;
//	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Set<Favourite> getFavourites() {
		return favourites;
	}

	public void setFavourites(Set<Favourite> favourites) {
		this.favourites = favourites;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getMoreImage() {
		return moreImage;
	}

	public void setMoreImage(String moreImage) {
		this.moreImage = moreImage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
