package shop.DTO;

import java.util.Set;

public class OrderRequest {

	private int userId;

//	private int voucherId;

	private int paymentId;
	
	private String paymentType;
	
	private boolean paymentStatus;

	private String nameReceiver;

	private String phoneReceiver;

	private String addressReceiver;
	
	private String emailReceiver;

	private double total;

	private double discount;

	private double shippingFee;

	private String note;

	private Boolean isConfirm;

	private Boolean isPay;

	private Boolean isCancle;
	
	private String cancleReason;
	
	private Boolean isDone;
	
	private Integer status;


	private Set<OrderDetailRequest> orderDetailRequests;

	public OrderRequest() {
		super();
	}

	public Set<OrderDetailRequest> getOrderDetailRequests() {
		return orderDetailRequests;
	}

	public void setOrderDetailRequests(Set<OrderDetailRequest> orderDetailRequests) {
		this.orderDetailRequests = orderDetailRequests;
	}

	public String getNameReceiver() {
		return nameReceiver;
	}

	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}

	public String getPhoneReceiver() {
		return phoneReceiver;
	}

	public void setPhoneReceiver(String phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}

	public String getAddressReceiver() {
		return addressReceiver;
	}

	public void setAddressReceiver(String addressReceiver) {
		this.addressReceiver = addressReceiver;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

//	public int getVoucherId() {
//		return voucherId;
//	}
//
//	public void setVoucherId(int voucherId) {
//		this.voucherId = voucherId;
//	}
//
//	public int getPaymentId() {
//		return paymentId;
//	}
//
//	public void setPaymentId(int paymentId) {
//		this.paymentId = paymentId;
//	}

	public Boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Boolean getIsPay() {
		return isPay;
	}

	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}

	public Boolean getIsCancle() {
		return isCancle;
	}

	public void setIsCancle(Boolean isCancle) {
		this.isCancle = isCancle;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCancleReason() {
		return cancleReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}
	
	
	
	
	

}
