package com.connectglobe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.connectglobe.model.Post;
import com.connectglobe.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	@Query(value="select * from postdetails p where p.id=?1",
			nativeQuery=true)
	List<Post> userPosts(int id);
	
}
