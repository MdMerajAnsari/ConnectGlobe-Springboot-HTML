package com.connectglobe.dto;

public class UserDto {
	private int id;	
	private String username;
	private String email;
	private String address;
	private String mobileNo;
	private String password;
	private String role;
	
	public UserDto()
	{
		
	}
	
	
	
	
	
	public UserDto(int id, String username, String email, String address, String mobileNo, String password,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.address = address;
		this.mobileNo = mobileNo;
		this.password = password;
		this.role = role;
	}





	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", address=" + address
				+ ", mobileNo=" + mobileNo + ", password=" + password + ", role=" + role + "]";
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
