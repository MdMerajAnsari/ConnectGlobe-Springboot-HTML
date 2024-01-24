package com.connectglobe.service;

import java.util.List;

import com.connectglobe.dto.CommentDto;
import com.connectglobe.model.Comment;



public interface CommentService {
	List<Comment> getAllComments();
	List<Comment> getAllCommentsByCid(long id);
	void saveComment(CommentDto comment);
	Comment getCommentById(long id);
	void deleteCommentById(long id);

}
