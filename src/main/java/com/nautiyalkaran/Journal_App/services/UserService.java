package com.nautiyalkaran.Journal_App.services;

import com.nautiyalkaran.Journal_App.Entity.User;
import com.nautiyalkaran.Journal_App.repository.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepoInterface userRepo;

    public void saveEntry(User newUser){
        userRepo.save(newUser);
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    public void updateUser(String id,User newuser){
        userRepo.deleteById(id);
        userRepo.save(newuser);
    }
    public User getUserByUserName(String username){
        return userRepo.findByusername(username);
    }




}
