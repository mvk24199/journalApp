package com.vamsi.journalApp.repository;

import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<UserEntry, ObjectId> {
    public UserEntry findByUsername(String username);
}
