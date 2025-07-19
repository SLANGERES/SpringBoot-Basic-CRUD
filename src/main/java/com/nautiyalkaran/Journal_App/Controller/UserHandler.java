package com.nautiyalkaran.Journal_App.Controller;

import com.nautiyalkaran.Journal_App.Entity.User;
import com.nautiyalkaran.Journal_App.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserService userdb;

    @GetMapping
    public ResponseEntity<List<User>>GetAlluser(){
        return new ResponseEntity<>(userdb.getUsers(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<String>AddNewUser(@RequestBody User newUser){
        userdb.saveEntry(newUser);
        return new ResponseEntity<>("User added successfully",HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<String>UpdateUser(@PathVariable("/username")String id,@RequestBody User newUser){
        userdb.updateUser(id,newUser);
        return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
    }
}

