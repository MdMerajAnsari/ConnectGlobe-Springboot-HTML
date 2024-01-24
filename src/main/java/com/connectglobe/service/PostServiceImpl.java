package com.connectglobe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectglobe.dto.PostDto;
import com.connectglobe.model.Post;
import com.connectglobe.repository.PostRepository;



@Service
public class PostServiceImpl implements PostService{

	@Autowired
	public PostRepository postRepository;
	@Override
	public List<Post> getAllPosts() {
		
		
		return postRepository.findAll();
	}
	

	@Override
	public void savePost(PostDto postDto) {
		Post post= new Post(postDto.getPostId(),postDto.getPostTitle(),postDto.getPostDate(),postDto.getPostDesc(),
				postDto.getPostCatagory(),postDto.getPhotoUrl(),postDto.getUserId());
		this.postRepository.save(post);
		
	}
	@Override
	public Post getPostById(long postId) {
		Optional<Post> optional= postRepository.findById(postId);
		Post post=null;
		if(optional.isPresent())
			post=optional.get();
		else 
			throw new NullPointerException("Post not found for id:: "+postId );
		return post;
		
	}
	@Override
	public void deletePostById(long postId) {
		this.postRepository.deleteById(postId);
		
	}
	@Override
	public List<Post> getAllPostsByUserId(int id) {
		List<Post> posts =postRepository.findAll();
		List<Post> postsByUser =new ArrayList<>();
		for(Post post:posts)
		{
			if(post.getUserId()==id)
			{
				postsByUser.add(post);
			}
		}
		return postsByUser;
	}
	
	
}
