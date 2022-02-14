package shop.DTO;

public class RatingRequest {
	
	
	private int userId;
	
	private int productId;
	
	private int star;
	
	private String content;

	public RatingRequest(int userId, int productId, int star, String content) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.star = star;
		this.content = content;
	}

	public RatingRequest() {
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
	
	
}
