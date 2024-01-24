package com.connectglobe;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.connectglobe.dto.CommentDto;
import com.connectglobe.model.Comment;
import com.connectglobe.model.Post;
import com.connectglobe.model.User;
import com.connectglobe.repository.CommentRepository;
import com.connectglobe.repository.PostRepository;
import com.connectglobe.repository.UserRepository;
import com.connectglobe.service.CommentServiceImp;
import com.connectglobe.service.PostServiceImpl;
import com.connectglobe.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootConnectGlobeApplicationTests {

	@Autowired
	private UserServiceImpl service;

	@MockBean
	private UserRepository repo;
	
	@Autowired
	private CommentServiceImp commentservice;

	@MockBean
	private CommentRepository commentrepo;

	Date currDate = new Date();
	

	@Autowired
	private PostServiceImpl postservice;

	@MockBean
	private PostRepository postrepo;


	/* Testing Comment Module */

	@Test
	void getAllCommentsTest() {
		when(commentrepo.findAll()).thenReturn(
				Stream.of(new Comment(200, "hi everyone", currDate, 100, 1001)).collect(Collectors.toList()));
		assertEquals(1, commentservice.getAllComments().size());
	}

	@Test
	 void getAllCommentsByCidTest() {
		when(commentrepo.findAll()).thenReturn(Stream.of(new Comment(200, "hi everyone", currDate, 100, 10),
				new Comment(201, "hi everyone", currDate, 100, 10), new Comment(202, "hi everyone", currDate, 100, 11),
				new Comment(203, "hi everyone", currDate, 100, 12)).collect(Collectors.toList()));
		assertEquals(2, commentservice.getAllCommentsByCid(10).size());
	}

	@Test
    void saveCommentTest() {

		Comment comment = new Comment(200, "happy puja", currDate, 100, 1001);
		when(commentrepo.save(comment)).thenReturn(comment);
		assertThat(comment, hasProperty("commentDesc", equals("happy puja")));

	}


	@Test
	 void deleteCommentByIdTest() {
		long commentid = 100;
		Comment comment = new Comment(200, "happy puja", currDate, 100, 1001);
		commentservice.deleteCommentById(100);
		verify(commentrepo, times(1)).deleteById(commentid);
	}

	private void assertThat(Comment comment, Object hasProperty) {
		// TODO Auto-generated method stub

	}

	private Object hasProperty(String string, boolean equals) {
		// TODO Auto-generated method stub
		return null;
	}
	
/* Testing Post Module */
	
	@Test
	void getAllPostsTest() {
		when(postrepo.findAll()).thenReturn(Stream.of(
				new Post(100, "durgapuja", "2022-08-08", "westbengal durga puja", "festivals", "Durgapuja.jpg", 111))
				.collect(Collectors.toList()));
		assertEquals(1, postservice.getAllPosts().size());
	}
	
	@Test
	void savepostTest() { 
		 Post post=new Post(105,"Hello world","2022-09-17","First Code","coding","Coding.jpg",121);
		 postrepo.save(post);
		 verify(postrepo,times(1)).save(post);
	 }
	 
	@Test
	void deletepostbyIdTest() {
			long postid=104;
		 Post post=new  Post(105,"Hello world","2022-09-17","First Code","coding","Coding.jpg",121);
		 postservice.deletePostById(104);
		 verify(postrepo,times(1)).deleteById(postid);
	}
	
	
	/* Testing user Module */
	@Test
    void saveUserTest() {
		User user=new User(100,"Meraj","meraj@gmail.com","Kumardhubi","8210038888","meraj","role_user");
		repo.save(user);
		verify(repo,times(1)).save(user);
	}
	
	@Test
	void deleteUserByIdTest() {
		int id=104;
		User user=new User(100,"Meraj","meraj@gmail.com","Kumardhubi","8210038888","meraj","role_user");
		service.deleteUserById(104);
		verify(repo,times(1)).deleteById(id);
	}
	
	@Test
	void getAllUsersTest() {
		when(repo.findAll()).thenReturn(Stream
				.of(new User(100, "rahul", "rahul@gmail.com", "kulti", "1234567890", "rahul", "ROLE_USER"),
						new User(111, "megha", "megha@gmail.com", "asansol", "1234567987", "megha", "ROLE_USER"))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAllUsers().size());
	}
	
	//Comment Model test cases
	
}