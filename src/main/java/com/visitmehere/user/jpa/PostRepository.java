package com.visitmehere.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitmehere.user.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
