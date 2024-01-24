package com.connectglobe.dto;





public class PostDto {
	
	private long postId;
	private String postTitle;
	private String postDate;
	private String postDesc;
	private String postCatagory;
	private String photoUrl;
	private int userId;
	
	

	
	public PostDto()
	{
		
	}

	

	public PostDto(long postId, String postTitle, String postDate, String postDesc, String postCatagory, String photoUrl,
			int userId) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postDesc = postDesc;
		this.postCatagory = postCatagory;
		this.photoUrl = photoUrl;
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "Post [postId=" + postId + ", PostTitle=" + postTitle + ", postDate=" + postDate + ", postDesc="
				+ postDesc + ", postCatagory=" + postCatagory + ", photoUrl=" + photoUrl + ", userId=" + userId +"]";
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}

	public String getPostCatagory() {
		return postCatagory;
	}

	public void setPostCatagory(String postCatagory) {
		this.postCatagory = postCatagory;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
	
}
