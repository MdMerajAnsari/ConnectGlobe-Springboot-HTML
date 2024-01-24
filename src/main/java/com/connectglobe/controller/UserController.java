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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.connectglobe.dto.UserDto;
import com.connectglobe.model.Comment;
import com.connectglobe.model.Post;
import com.connectglobe.model.User;

import com.connectglobe.repository.UserRepository;
import com.connectglobe.service.CommentService;
import com.connectglobe.service.PostService;
import com.connectglobe.service.UserService;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Controller
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	public static final String ADMIN="redirect:/admin/adminUserIndex";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	/* Locating to useIndex page with posts,comments*/ 
	@GetMapping("/user/home")
	   public String home(Model m,Authentication authentication)
	{
		BasicConfigurator.configure();
		User user =userRepository.findByEmail(authentication.getName());
		List<Post> posts=postService.getAllPosts();
		List<User> users =userService.getAllUsers();
		List<Comment> comments=commentService.getAllComments();
		m.addAttribute("currUser",user);
		m.addAttribute("posts",posts);
		m.addAttribute("users",users);
		m.addAttribute("comments",comments);
		for(Comment comment:comments)
			logger.info(comment.getCommentDesc());
	          return "RegisteredUser/userIndex";                      
	 }
	
	/*Locating to userIndex */
	@GetMapping("/user")
	public String home(Model m) {		
		List<User> list=userService.getAllUsers();
		m.addAttribute("allUser", list);
		return "userIndex";
	}
	
	/* Locating to AdminUserIndex with All users */
	@GetMapping("/admin/adminUserIndex")
	public String adminAddUserIndex(Model m,Authentication authentication) {
		User user =userRepository.findByEmail(authentication.getName());
		
		List<User> list=userService.getAllUsers();
		m.addAttribute("allUser", list);
		m.addAttribute("curr_user",user);
		return "Admin/adminUserIndex";
	}
	
	
	/* locating to Add user from Admin module */
	@GetMapping("/admin/loadform")
	public String adminLoadForm(Model m,Authentication authentication) {
		User user =userRepository.findByEmail(authentication.getName());
		m.addAttribute("curr_user",user);
		return "Admin/adminAddUser";
	}
	
	/* locating to Edit user by user id from Admin module */
	@GetMapping("/admin/updateform/{id}")
	public String adminUpdateForm(@PathVariable(value="id") int id,Model m) {
		
		BasicConfigurator.configure();
	    User user=userService.getUserById(id);
		m.addAttribute("user", user);
		logger.info(user);
		return "Admin/adminEditUser";
	}
    
	
	
	/* save use to database form Admin module */
	@PostMapping("/admin/saveuser")
	public String adminSaveUser(@ModelAttribute UserDto user, HttpSession session) {
		
			user.setPassword(bp.encode(user.getPassword()));
			user.setRole("ROLE_USER");
			userService.saveUser(user);
		  
			session.setAttribute("msg","User Added Successfully");
		
		
		return ADMIN;
	}
	
	/* Updating user data */
	@PostMapping("/updateuser")
	public String updateUser(@ModelAttribute UserDto user,HttpSession session) {
		  userService.saveUser(user);
		  session.setAttribute("msg","User Updated Successfully");		
		
		return "redirect:/user";
		  
	}
	
	/* Locating to update user form from Admin module */
	@PostMapping("/admin/updateuser")
	public String adminUpdateUser(@ModelAttribute UserDto user,HttpSession session) {
		  userService.saveUser(user);
		  session.setAttribute("msg","User Updated Successfully");		
		
		return ADMIN;
		  
	}
	
	/* delete user form from Admin module */
	@GetMapping("/admin/deleteUser/{id}")
	public String adminDeleteUser(@PathVariable(value="id") int id,HttpSession session) {
		userService.deleteUserById(id);
		session.setAttribute("msg", "User Deleted Successfully");
		return ADMIN;
	}

	/* delete user */
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value="id") int id,HttpSession session) {
		userService.deleteUserById(id);
		session.setAttribute("msg", "User Deleted Successfully");
		return "redirect:/user";
	}
}
