package com.nautiyalkaran.Journal_App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journalEntries")
public class JournalEntries {

    @Id
    private String uid;

    private String title;

    private String Content;

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

