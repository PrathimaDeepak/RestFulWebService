package com.visitmehere.user.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.visitmehere.user.Post;
import com.visitmehere.user.User;
import com.visitmehere.user.exceptions.UserNotFoundException;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@GetMapping(path="/jpa/users")
	public List<User> retriveAllUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public User retreiveUser(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id = " + id);
		}
		
		return user.get();
	}
	
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepo.deleteById(id);
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retriveAllUserPosts(@PathVariable int id){
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id = " + id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> createUserPosts(@PathVariable int id, @RequestBody Post post) {
		User user = userRepo.findById(id).get();
		if(user == null) {
			throw new UserNotFoundException("id = " + id);
		}
		post.setUser(user);
		postRepo.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/jpa/posts/{id}")
	public void deletePost(@PathVariable int id) {
		postRepo.deleteById(id);
	}


}
