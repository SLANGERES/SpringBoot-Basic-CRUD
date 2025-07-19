package com.nautiyalkaran.Journal_App.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journalEntries")
@Data
@NoArgsConstructor
public class JournalEntries {

    @Id
    private String uid;
    @NonNull
    private String title;

    private String Content;
}

