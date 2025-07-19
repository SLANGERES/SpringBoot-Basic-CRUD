package com.nautiyalkaran.Journal_App.repository;

import com.nautiyalkaran.Journal_App.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepoInterface extends MongoRepository<User,String> {
    User findByusername(String username);
}
