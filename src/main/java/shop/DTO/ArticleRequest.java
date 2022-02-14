package shop.DTO;

public class ArticleRequest {
	
	private String name;

	private String slug;

	private Boolean isHot;

	private String shortDesc;

	private String description;

	private Boolean isActive;
	
	private int categoryArticleId;

	public ArticleRequest(String name, String slug, Boolean isHot, String shortDesc, String description,
			Boolean isActive, int categoryArticleId) {
		super();
		this.name = name;
		this.slug = slug;
		this.isHot = isHot;
		this.shortDesc = shortDesc;
		this.description = description;
		this.isActive = isActive;
		this.categoryArticleId = categoryArticleId;
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

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getCategoryArticleId() {
		return categoryArticleId;
	}

	public void setCategoryArticleId(int categoryArticleId) {
		this.categoryArticleId = categoryArticleId;
	}
	
	
	
	
	
	
}
