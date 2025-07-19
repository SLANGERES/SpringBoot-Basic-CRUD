package com.nautiyalkaran.Journal_App.Controller;

import com.nautiyalkaran.Journal_App.Entity.JournalEntries;
import com.nautiyalkaran.Journal_App.Entity.User;
import com.nautiyalkaran.Journal_App.services.JournalService;
import com.nautiyalkaran.Journal_App.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal")

public class Handler {
    @Autowired
    private JournalService dbService;
    @Autowired
    private UserService dbUser;


    @PostMapping("/{username}")
    public ResponseEntity<String> postJournalEntry(@RequestBody JournalEntries reqEntry,@PathVariable("username") String username) {
        try{
            dbService.saveEntry(reqEntry,username);
        } catch (Exception e) {
            return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Journal created successfully ", HttpStatus.CREATED);
    }

    @GetMapping("/{username}/{uid}")
    public ResponseEntity<JournalEntries> getJournalEntiresById(@PathVariable("uid") String id,
                                                         @PathVariable("username") String username) {
        User newUser = dbUser.getUserByUserName(username);
        Optional<JournalEntries> data = newUser.getJournalById(id); // <-- corrected this line
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntries>> getJournalEntry(@PathVariable("username") String username) {
        User user = dbUser.getUserByUserName(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<JournalEntries> journalEntries = user.getJournal();
        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @DeleteMapping("/{username}/{uid}")
    public ResponseEntity<String> deleteJournalById(@PathVariable("uid") String id,@PathVariable("username") String uname){

        User newUser=dbUser.getUserByUserName(uname);
        newUser.deleteByid(id);
        dbUser.saveEntry(newUser);
        dbService.deleteById(id);

        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<JournalEntries> updateJournalById(@RequestBody JournalEntries newJournal , @PathVariable("uid") String id){
        try{
            JournalEntries data=dbService.updateById(id,newJournal);
            return new ResponseEntity<>(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
}
