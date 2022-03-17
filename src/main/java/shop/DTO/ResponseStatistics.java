package shop.DTO;

import java.util.List;

public class ResponseStatistics {

	private Long totalUser;

	private Long totalOrder;

	private Long totalProduct;

	private Long totalArticle;

	private Double totalRevenue;
	
	private List<Double> revenueByMonthly;
	
	

	public List<Double> getRevenueByMonthly() {
		return revenueByMonthly;
	}

	public void setRevenueByMonthly(List<Double> revenueByMonthly) {
		this.revenueByMonthly = revenueByMonthly;
	}

	public Long getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(Long totalUser) {
		this.totalUser = totalUser;
	}

	public Long getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Long totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Long getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Long totalProduct) {
		this.totalProduct = totalProduct;
	}

	public Long getTotalArticle() {
		return totalArticle;
	}

	public void setTotalArticle(Long totalArticle) {
		this.totalArticle = totalArticle;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public ResponseStatistics() {
		super();
	}

}
