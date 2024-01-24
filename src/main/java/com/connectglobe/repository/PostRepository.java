package com.connectglobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectglobe.model.Post;



@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
