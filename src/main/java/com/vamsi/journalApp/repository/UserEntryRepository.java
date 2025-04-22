package com.vamsi.journalApp.repository;

import com.vamsi.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
    public User findByUsername(String username);
}
