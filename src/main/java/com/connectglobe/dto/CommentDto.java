package com.connectglobe.dto;

import java.util.Date;

public class CommentDto {
	
	private long commentId;
	private String commentDesc;
	private Date commentDate;
	private int cPostId;
	private int cId;
	
	public CommentDto() {}


	public CommentDto(long commentId, String commentDesc, Date commentDate, int cPostId, int cId) {
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


	


	
	
	
}
