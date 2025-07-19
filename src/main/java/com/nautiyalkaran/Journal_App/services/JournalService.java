package com.nautiyalkaran.Journal_App.services;


import com.nautiyalkaran.Journal_App.Entity.JournalEntries;
import com.nautiyalkaran.Journal_App.Entity.User;
import com.nautiyalkaran.Journal_App.repository.JournalRepoInterface;
import com.nautiyalkaran.Journal_App.repository.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService{

    @Autowired
    private JournalRepoInterface JournalRepo;

    @Autowired
    private UserRepoInterface UserRepo;


    public void saveEntry(JournalEntries newJournalEntry, String username) {
        User user = UserRepo.findByusername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        JournalEntries savedEntry = JournalRepo.save(newJournalEntry);
        user.getJournal().add(savedEntry);
        UserRepo.save(user);
    }


    public List<JournalEntries> getAll(){
        return JournalRepo.findAll();
    }
    public Optional<JournalEntries> getById(String id){
        return JournalRepo.findById(id);

    }
    public void deleteById(String id){
      JournalRepo.deleteById(id);

    }
    public JournalEntries updateById(String id,JournalEntries newJournalEntry){
        JournalRepo.deleteById(id);
        JournalRepo.save(newJournalEntry);
        return newJournalEntry;
    }
}

// controller calls --> Services service call Repo -----> Repo