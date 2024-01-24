package com.connectglobe.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.connectglobe.dto.PostDto;
import com.connectglobe.model.Post;
import com.connectglobe.model.User;
import com.connectglobe.repository.UserRepository;
import com.connectglobe.service.PostService;
import com.connectglobe.service.UserService;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Controller
public class PostController {
	private static final Logger logger = LogManager.getLogger(PostController.class);
	@Autowired
	private PostService postservice;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	/* Locating to usepostindex with current user,all posts */
	@GetMapping("/user/userPostIndex")
	public String showPostIndexByUserId(Model m,Authentication authentication)
	{
		BasicConfigurator.configure();
		User user =userRepository.findByEmail(authentication.getName());
		List<Post> list=postservice.getAllPostsByUserId(user.getId());
		
		logger.info(user);
		
		m.addAttribute("all_posts",list);
		m.addAttribute("userobjUPI",user);
		
		return "RegisteredUser/userPostIndex";
	}
	
	/* Locating to usepostindex with current user,all posts */
	
	@GetMapping("/admin/adminPostIndex")
	public String showPostIndexToAdmin(Model m,Authentication authentication)
	{
		BasicConfigurator.configure();
		
		User user =userRepository.findByEmail(authentication.getName());
		List<Post> list=postservice.getAllPosts();
		List<User> users=userService.getAllUsers();
		
		logger.info(user);
		
		m.addAttribute("all_posts",list);
		m.addAttribute("userobjAPI",user);
		m.addAttribute("users",users);
		
		return "Admin/adminPostIndex";
	}
	
	
	
	/* displaying add post form to user module */
	@GetMapping("/user/addPost")
	public String userLoadform(Model m,Authentication authentication)
	{
		User user =userRepository.findByEmail(authentication.getName());
		m.addAttribute("userobjUAP",user);
		return "RegisteredUser/userAddPost";
	}
	
	/* displaying add post form to Admin module */
	@GetMapping("/admin/adminAddPost")
	public String amdminLoadform(Model m,Authentication authentication)
	{
		User user =userRepository.findByEmail(authentication.getName());
		m.addAttribute("userobjAAP",user);
		return "Admin/adminAddPost";
	}
	
	/* displaying edit post form to user module */
	@GetMapping("/user/edit_form/{post_id}")
	public String userEditform(@PathVariable(value ="post_id") long postid,Model m,Authentication authentication)
	{
		User user =userRepository.findByEmail(authentication.getName());
		m.addAttribute("userobjUEP",user);
		
		Post post=postservice.getPostById(postid);
		m.addAttribute("post1",post);
		return "RegisteredUser/userEditPost";
	}
	
	/* displaying edit post form to Admin module */
	@GetMapping("/admin/edit_form/{post_id}")
	public String adminEditform(@PathVariable(value ="post_id") long postId,Model m,Authentication authentication)
	{
		User user =userRepository.findByEmail(authentication.getName());
		m.addAttribute("userobj",user);
		
		Post post=postservice.getPostById(postId);
		m.addAttribute("post1",post);
		return "admin/adminEditPost";
	}

	
	/*save  user post from user module */
	@PostMapping("/user/save_post")
	public String userSavePosts(@ModelAttribute PostDto post) {
		postservice.savePost(post);
		
		return "redirect:/user/userPostIndex";
		
	}
	
	/*save  admin post from Admin module */
	@PostMapping("/admin/save_post")
	public String adminSavePosts(@ModelAttribute PostDto post) {
		
		postservice.savePost(post);
		
		return "redirect:/admin/adminPostIndex";

	}
	

	/*update  user post from user module */
	@PostMapping("/user/update_post")
	public String userUpdatePost(@ModelAttribute PostDto post) {
		

		return userSavePosts(post);
		
	}
	/*update  Admin post from Admin module */
	@PostMapping("/admin/update_post")
	public String adminUpdatePost(@ModelAttribute PostDto post) {
		

		return adminSavePosts(post);
	}
	
	
	/*delete  user post from user module */
	@GetMapping("/user/deletePost/{post_id}")
	public String userDeletePost(@PathVariable(value ="post_id") long postid)
	{
		postservice.deletePostById(postid);
		return "redirect:/user/userPostIndex";
	}
	
	/*delete  any post from Admin module */
	@GetMapping("/admin/deletePost/{post_id}")
	public String adminDeletePost(@PathVariable(value ="post_id") long postId)
	{
		postservice.deletePostById(postId);
		return "redirect:/admin/adminPostIndex";
	}
}