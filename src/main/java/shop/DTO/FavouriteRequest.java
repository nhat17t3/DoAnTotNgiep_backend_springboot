package shop.DTO;

public class FavouriteRequest {

	private int userId;

	private int productId;

	public FavouriteRequest() {
		super();
	}

	public FavouriteRequest(int userId, int productId) {
		super();
		this.userId = userId;
		this.productId = productId;
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

}
