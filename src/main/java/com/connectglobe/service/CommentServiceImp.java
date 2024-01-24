package com.connectglobe.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectglobe.dto.CommentDto;
import com.connectglobe.model.Comment;
import com.connectglobe.repository.CommentRepository;



@Service
public class CommentServiceImp implements CommentService{

	@Autowired
	public CommentRepository commentRepository;
	
	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}
	
	@Override
	public List<Comment> getAllCommentsByCid(long id) {
		List<Comment> comments=commentRepository.findAll();
		List<Comment> commentsByCid=new ArrayList<>();
		for(Comment comment:comments)
		{
			if(comment.getcId()==id)
			{
				commentsByCid.add(comment);
			}
		}
		return commentsByCid;
	}
	
	@Override
	public void  saveComment(CommentDto commentDto) {
		Comment comment=new Comment(commentDto.getCommentId(),commentDto.getCommentDesc(),commentDto.getCommentDate(),
				commentDto.getcPostId(),commentDto.getcId());
		this.commentRepository.save(comment);
	}

	@Override
	public Comment getCommentById(long id) {
		Optional<Comment> optional= commentRepository.findById(id);
		Comment comment=null;
		if(optional.isPresent())
			comment=optional.get();
		else 
			throw new NullPointerException("Comment not found for id:: "+id );
		return comment;
	}

	@Override
	public void deleteCommentById(long id) {
		
		this.commentRepository.deleteById(id);
	}

	
	
}
