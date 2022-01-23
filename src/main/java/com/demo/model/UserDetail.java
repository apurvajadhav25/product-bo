package com.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String username;
	String password;
	String mobileNumber;
	String emailId;
	String isEnable;
	String wishlistIds;
	String cartIds;
	
	@OneToMany(mappedBy = "userDetail")
	Set<Order> orders;
	
	public UserDetail() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	public String getWishlistIds() {
		return wishlistIds;
	}
	public void setWishlistIds(String wishlistIds) {
		this.wishlistIds = wishlistIds;
	}
	public String getCartIds() {
		return cartIds;
	}
	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}

	public UserDetail(int id, String username, String password, String mobileNumber, String emailId, String isEnable,
			String wishlistIds, String cartIds) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.isEnable = isEnable;
		this.wishlistIds = wishlistIds;
		this.cartIds = cartIds;
	}

	

	
	
	
	

}
