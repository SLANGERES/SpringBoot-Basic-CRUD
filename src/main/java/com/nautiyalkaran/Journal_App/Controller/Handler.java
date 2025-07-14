package com.nautiyalkaran.Journal_App.Controller;

import com.nautiyalkaran.Journal_App.Entity.JournalEntries;
import com.nautiyalkaran.Journal_App.services.JournalService;
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


    @PostMapping("/journal")
    public ResponseEntity<String> postJournalEntry(@RequestBody JournalEntries reqEntry) {
        try{
            dbService.saveEntry(reqEntry);
        } catch (Exception e) {
            return new ResponseEntity<>("",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Journal created successfully ", HttpStatus.CREATED);
    }

    @GetMapping("/id/{uid}")
    public ResponseEntity<JournalEntries> getJournalById(@PathVariable("uid") String id) {
        Optional<JournalEntries> data = dbService.getById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<JournalEntries>> getJournalEntry(){
        List<JournalEntries>data=dbService.getAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @DeleteMapping("/id/{uid}")
    public ResponseEntity<String> deleteJournalById(@PathVariable("uid") String id){
        Optional<JournalEntries> newEntry=dbService.getById(id);
        if (newEntry.isPresent()){
            return new ResponseEntity<>("NOT FOUND",HttpStatus.NOT_FOUND);
        }
        dbService.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("id/{uid}")
    public ResponseEntity<JournalEntries> updateJournalById(@RequestBody JournalEntries newJournal , @PathVariable("uid") String id){
        try{
            JournalEntries data=dbService.updateById(id,newJournal);
            return new ResponseEntity<>(data,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
}
