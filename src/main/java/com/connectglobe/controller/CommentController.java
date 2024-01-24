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

import com.connectglobe.dto.CommentDto;
import com.connectglobe.model.Comment;
import com.connectglobe.model.Post;
import com.connectglobe.model.User;

import com.connectglobe.repository.UserRepository;
import com.connectglobe.service.CommentService;
import com.connectglobe.service.PostService;



@Controller
public class CommentController {
	

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired PostService postService;
	
	/* display list of comments,post and Current user in User moduel */
	@GetMapping("/user/userCommentIndex")
	public String viewHomePage(Model model,Authentication authentication)
	{
		User user =userRepository.findByEmail(authentication.getName());
		
		List<Post> posts=postService.getAllPosts();
		
		model.addAttribute("userobjCI",user);
		model.addAttribute("posts",posts);
		model.addAttribute("listComments",commentService.getAllCommentsByCid(user.getId()));
		return "RegisteredUser/userCommentIndex";
	}
	
	/* display list of comments,post and Current user in Admin moduel */
	@GetMapping("/admin/adminCommentIndex")
	public String viewAdminHomePage(Model model,Authentication authentication)
	{
		User user =userRepository.findByEmail(authentication.getName());
		
		List<Post> posts=postService.getAllPosts();
		
		model.addAttribute("userobjACI",user);
		model.addAttribute("posts",posts);
		model.addAttribute("listComments",commentService.getAllComments());
		return "Admin/adminCommentIndex";
	}
	
	
	/* Save comment from User Module */
	@PostMapping("/user/saveComment")
	public String userSaveComment(@ModelAttribute("comment") CommentDto comment)
	{
		//save comment to database
		commentService.saveComment(comment);
		return "redirect:/user/userCommentIndex";
		
	}
	
	/* Save comment from Admin Module */
	@PostMapping("/admin/saveComment")
	public String adminSaveComment(@ModelAttribute("comment") CommentDto comment)
	{
		/* save comment to database */
		commentService.saveComment(comment);
		return "redirect:/admin/adminCommentIndex";
		
	}
	
	/* Save comment by post from User Module */
	@PostMapping("/user/saveCommentByPost")
	public String userSaveCommentByPost(@ModelAttribute("comment") CommentDto comment)
	{
		/* save comment to database */
		commentService.saveComment(comment);
		return "redirect:/user/home";
		
	}
	
	/* Save comment by post from Admin Module */
	@PostMapping("/admin/saveCommentByPost")
	public String adminSaveCommentByPost(@ModelAttribute("comment") CommentDto comment)
	{
		/* save comment to database */
		commentService.saveComment(comment);
		return "redirect:/admin/adminIndex";
		
	}
	

	/* displaying comment by post id to edit comment  from User Module */
	@GetMapping("/user/showFormForUpdate/{id}")
	public String userCommentUpdate(@PathVariable(value ="id") long id,Model model,Authentication authentication)
	{
		/* get comment from the service */
		User user =userRepository.findByEmail(authentication.getName());
		model.addAttribute("userobjUEC",user);
		Comment comment=commentService.getCommentById(id);
		model.addAttribute("comment",comment);
		return "RegisteredUser/userEditComment";
	}
	
	/* displaying comment by post id to edit comment  from Admin Module */
	@GetMapping("/admin/showFormForUpdate/{id}")
	public String adminCommentUpdate(@PathVariable(value ="id") long id,Model model,Authentication authentication)
	{
		//get comment from the service 
		User user =userRepository.findByEmail(authentication.getName());
		model.addAttribute("userobj",user);
		Comment comment=commentService.getCommentById(id);
		model.addAttribute("comment",comment);
		return "admin/adminEditComment";
	}
	
	/* deleting comment  id  from User Module */
	@GetMapping("/user/deleteComment/{id}")
	public String userDeleteComment(@PathVariable(value="id") long id)
	{
		/* delete Comment method */
		this.commentService.deleteCommentById(id);
		return "redirect:/user/userCommentIndex";
		
	}
	
	/* deleting comment  id  from Admin Module */
	@GetMapping("/admin/deleteComment/{id}")
	public String adminDeleteComment(@PathVariable(value="id") long id)
	{
		/* delete Comment method */
		this.commentService.deleteCommentById(id);
		return "redirect:/admin/adminCommentIndex";
		
	}
	
}
