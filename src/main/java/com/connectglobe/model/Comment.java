package com.connectglobe.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long commentId;
	
	private String commentDesc;
	
	@Column(name = "comment_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Date commentDate;
	
	private int cPostId;
	
	private int cId;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post post;
	
	
	public Comment() {}


	public Comment(long commentId, String commentDesc, Date commentDate, int cPostId, int cId) {
		super();
		this.commentId = commentId;
		this.commentDesc = commentDesc;
		this.commentDate = commentDate;
		this.cPostId = cPostId;
		this.cId = cId;
		
	}
	
	


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentDesc=" + commentDesc + ", commentDate=" + commentDate
				+ ", cPostId=" + cPostId + ", cId=" + cId + "]";
	}


	public long getCommentId() {
		return commentId;
	}


	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}


	public String getCommentDesc() {
		return commentDesc;
	}


	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}


	public Date getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	public int getcPostId() {
		return cPostId;
	}


	public void setcPostId(int cPostId) {
		this.cPostId = cPostId;
	}


	public int getcId() {
		return cId;
	}


	public void setcId(int cId) {
		this.cId = cId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}
	
	
	


	

}
