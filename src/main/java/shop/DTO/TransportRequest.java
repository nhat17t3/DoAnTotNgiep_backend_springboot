package shop.DTO;

public class TransportRequest {
	
	private int transporterId;
	
	private int orderId;

	private String code;

	private double codPrice;

	private String fromAddress;

	private String toAddress;
	
	private Boolean isDone;
	
	private Boolean isCancle;
	
	

	public TransportRequest(int transporterId, int orderId, String code, double codPrice, String fromAddress,
			String toAddress) {
		super();
		this.transporterId = transporterId;
		this.orderId = orderId;
		this.code = code;
		this.codPrice = codPrice;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
	}

	public int getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(int transporterId) {
		this.transporterId = transporterId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getCodPrice() {
		return codPrice;
	}

	public void setCodPrice(double codPrice) {
		this.codPrice = codPrice;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Boolean getIsCancle() {
		return isCancle;
	}

	public void setIsCancle(Boolean isCancle) {
		this.isCancle = isCancle;
	}
	
	

	
	
}
