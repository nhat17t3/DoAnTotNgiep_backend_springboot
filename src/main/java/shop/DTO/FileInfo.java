package shop.DTO;

public class FileInfo {
	private String fileName;
	private String fileDownloadUri;

	public FileInfo(String fileName, String fileDownloadUri) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

}