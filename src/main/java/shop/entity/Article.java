package shop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "article")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String slug;

	@Column(name = "is_hot")
	private Boolean isHot;

	@Column
	private int view;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column
	private String description;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name="category_article_id")
	private CategoryArticle categoryArticle;

//	Constructor
	public Article() {
		super();
	}

	public Article(String name, String slug, Boolean isHot,  String shortDesc, String desciption,
			Boolean isActive, LocalDateTime createdAt) {
		super();
		this.name = name;
		this.slug = slug;
		this.isHot = isHot;
		this.shortDesc = shortDesc;
		this.description = desciption;
		this.isActive = isActive;
		this.createdAt = createdAt;
	}

	// getter and setter
	
	

	public CategoryArticle getCategoryArticle() {
		return categoryArticle;
	}

	public void setCategoryArticle(CategoryArticle categoryArticle) {
		this.categoryArticle = categoryArticle;
	}
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
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

	public void setUpdatedAt(LocalDateTime time) {
		this.updatedAt = time;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
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

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
