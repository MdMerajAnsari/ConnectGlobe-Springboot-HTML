package com.connectglobe.service;

import java.util.List;

import com.connectglobe.dto.PostDto;
import com.connectglobe.model.Post;




public interface PostService {
	List<Post> getAllPosts();
	void savePost(PostDto post);
	Post getPostById(long postId);
	void deletePostById(long postId);
	List<Post> getAllPostsByUserId(int id);
	
}
