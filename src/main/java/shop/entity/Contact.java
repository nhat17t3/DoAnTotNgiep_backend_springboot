package shop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name_company")
	private String nameCompany;

	@Column
	private String phone;

	@Column
	private String address;

	@Column
	private String email;

	@Column(name = "link_facebook")
	private String linkFacebook;

	@Column(name = "link_instagram")
	private String linkInstagram;

	@Column
	private String bank;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

//	Constructor
	public Contact() {
		super();
	}

	public Contact(String nameCompany, String phone, String address, String email, String linkFacebook,
			String linkInstagram, String bank) {
		super();
		this.nameCompany = nameCompany;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.linkFacebook = linkFacebook;
		this.linkInstagram = linkInstagram;
		this.bank = bank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkFacebook() {
		return linkFacebook;
	}

	public void setLinkFacebook(String linkFacebook) {
		this.linkFacebook = linkFacebook;
	}

	public String getLinkInstagram() {
		return linkInstagram;
	}

	public void setLinkInstagram(String linkInstagram) {
		this.linkInstagram = linkInstagram;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
