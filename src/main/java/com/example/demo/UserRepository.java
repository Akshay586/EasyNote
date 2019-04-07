package com.example.demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	
	public User findByEmailId(String emailId);
	public User findByUserName(String userName);
}
