package com.example.proxybandtechnicaltask.repositories;

import com.example.proxybandtechnicaltask.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}
