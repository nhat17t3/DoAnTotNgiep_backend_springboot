package shop.DTO;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {
	
	private int brandId;
	
	private Set<Integer> categories;
	
	private String name;

	private String slug;

	private String code;

	private MultipartFile image;

	private double unitPrice;

	private double promotionPrice;

	private int instock;

	private String shortDesc;

	private String description;

	private String ingredient;

	private String specification;

	private Boolean isHot;

	private Boolean isNew;

	private Boolean isActive;
	
	

	

	public ProductRequest() {
		super();
	}

	public ProductRequest(String name, String slug, String code, MultipartFile image, double unitPrice,
			double promotionPrice, int instock, String shortDesc, String description, String ingredient,
			String specification, Boolean isHot, Boolean isNew, Boolean isActive, int brandId) {
		super();
		this.name = name;
		this.slug = slug;
		this.code = code;
		this.image = image;
		this.unitPrice = unitPrice;
		this.promotionPrice = promotionPrice;
		this.instock = instock;
		this.shortDesc = shortDesc;
		this.description = description;
		this.ingredient = ingredient;
		this.specification = specification;
		this.isHot = isHot;
		this.isNew = isNew;
		this.isActive = isActive;
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
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

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public Set<Integer> getCategories() {
		return categories;
	}

	public void setCategories(Set<Integer> categories) {
		this.categories = categories;
	}
	
	

}
