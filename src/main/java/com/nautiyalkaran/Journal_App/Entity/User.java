package com.nautiyalkaran.Journal_App.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Document(collection = "User")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntries> journal = new ArrayList<>();

    public Optional<JournalEntries> getJournalById(String entryId) {
        if (journal == null) return Optional.empty();

        //filter from the journal stream
        return journal.stream()
                .filter(entry -> entry.getUid().equals(entryId))
                .findFirst();
    }
    public void deleteByid(String id){
        journal.removeIf(journalEntries -> journalEntries.getUid().equals(id));
    }
}
