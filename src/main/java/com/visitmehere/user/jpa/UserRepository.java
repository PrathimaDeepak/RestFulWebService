package com.visitmehere.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitmehere.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
