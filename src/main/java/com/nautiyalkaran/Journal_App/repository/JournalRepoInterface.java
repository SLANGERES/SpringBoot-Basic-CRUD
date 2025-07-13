package com.nautiyalkaran.Journal_App.repository;


import com.nautiyalkaran.Journal_App.Entity.JournalEntries;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepoInterface extends MongoRepository<JournalEntries,String> {
}
