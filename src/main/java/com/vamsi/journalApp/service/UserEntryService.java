package com.vamsi.journalApp.service;

import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.entity.UserEntry;
import com.vamsi.journalApp.repository.JournalEntryRepository;
import com.vamsi.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(UserEntry userEntry){
        userEntryRepository.save(userEntry);
    }
    public List<UserEntry> getAllUsers(){
        return userEntryRepository.findAll();
    }
    public UserEntry getJournalById(ObjectId id){
        return userEntryRepository.findById(id).orElse(null);
    }
    public void deleteById(ObjectId id){userEntryRepository.deleteById(id);}
    public UserEntry findByUsername(String username){
        return userEntryRepository.findByUsername(username);
    }
}
