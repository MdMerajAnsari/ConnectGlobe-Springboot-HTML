package com.connectglobe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.connectglobe.dto.UserDto;
import com.connectglobe.model.Comment;
import com.connectglobe.model.Post;
import com.connectglobe.model.User;
import com.connectglobe.repository.UserLoginRepository;
import com.connectglobe.repository.UserRepository;
import com.connectglobe.service.CommentService;
import com.connectglobe.service.PostService;
import com.connectglobe.service.UserService;

@Controller
public class UserLoginController {

	@Autowired
    private UserLoginRepository userLoginRepository;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	

	/* Locating to Index Page */
	@GetMapping("/login")
	public String login() {
		return "index";
	}
	
	/* Locating to Anonymous page with posts and comments */
	@GetMapping("/anonymous")
	public String anonymous(Model m, Authentication authentication)
	{
		
		List<Post> posts=postService.getAllPosts();
		List<User> users =userService.getAllUsers();
		List<Comment> comments=commentService.getAllComments();
		
		m.addAttribute("posts",posts);
		m.addAttribute("users",users);
		m.addAttribute("comments",comments);
		
		return "Anonymous";
	}
	
	/* registration by new user */
	@PostMapping("/registration")
	public String register(@ModelAttribute UserDto user,HttpSession session) {
		
		user.setPassword(bp.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		userService.saveUser(user);
		
		session.setAttribute("message", "User registered sucessfull");
		return "index";
	}
	
	/* Locating to registeration page */
	@GetMapping("/register") 
	public String showRegistrationForm(Model model) {
	  model.addAttribute("user", new User());
	  
	 return "register"; 
	 }
	 
	/* Locating to Admin Index with posts and comments  */
	@GetMapping("/admin/adminIndex")
	public String adminIndex(Model m, Authentication authentication)
	{
		
		User user =userRepository.findByEmail(authentication.getName());
		List<Post> posts=postService.getAllPosts();
		List<User> users =userService.getAllUsers();
		List<Comment> comments=commentService.getAllComments();
		m.addAttribute("curr_user",user);
		m.addAttribute("posts",posts);
		m.addAttribute("users",users);
		m.addAttribute("comments",comments);
		
		 
			 return "Admin/adminIndex"; 
			
	}
	

}

