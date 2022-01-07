package shop.DTO;

public class ResponseObject {
	
	private String status;
	
	private String message;
	
	private Object dataResponse;

	public ResponseObject(String status, String message, Object dataResponse) {
		super();
		this.status = status;
		this.message = message;
		this.dataResponse = dataResponse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDataResponse() {
		return dataResponse;
	}

	public void setDataResponse(Object dataResponse) {
		this.dataResponse = dataResponse;
	}
	
	
	
}
