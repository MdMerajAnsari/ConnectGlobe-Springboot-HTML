package com.connectglobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectglobe.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	

}
