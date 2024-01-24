package com.connectglobe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user",uniqueConstraints = @UniqueConstraint(columnNames="email"))
public class User  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String email;
	private String address;
	private String mobileNo;
	private String password;
	
	
	@Column(columnDefinition = "varchar(255) default 'reg_user'")
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<Post> posts;
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<Comment> comments;
	
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
	
	
	public User() {
		super();
	}
	public User(int id, String username, String email, String address, String mobileNo, String password, String role) {
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
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", address=" + address + ", mobileNo="
				+ mobileNo + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
	
}
