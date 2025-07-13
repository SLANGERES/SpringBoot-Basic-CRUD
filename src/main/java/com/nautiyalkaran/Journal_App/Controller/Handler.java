package com.nautiyalkaran.Journal_App.Controller;

import com.nautiyalkaran.Journal_App.Entity.JournalEntries;
import com.nautiyalkaran.Journal_App.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal")
public class Handler {
    @Autowired
    private JournalService dbService;


    @PostMapping
    public boolean postJournalEntry(@RequestBody JournalEntries reqEntry){
        dbService.saveEntry(reqEntry);
        return true;
    }
    @GetMapping("/id/{uid}")
    public JournalEntries getJournalById(@PathVariable("uid") String id){
        return dbService.getById(id);
    }

    @GetMapping
    public List<JournalEntries> getJournalEntry(){
        return dbService.getAll();
    }

    @DeleteMapping("/id/{uid}")
    public JournalEntries deleteJournalById(@PathVariable("uid") String id){
        JournalEntries newEntry=dbService.getById(id);
        dbService.deleteById(id);
        return newEntry;
    }

    @PutMapping("id/{uid}")
    public JournalEntries updateJournalById(@RequestBody JournalEntries newJournal , @PathVariable("uid") String id){
        return dbService.updateById(id,newJournal);
    }
}
