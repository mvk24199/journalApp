package com.vamsi.journalApp.service;

import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllJournals(){
        return journalEntryRepository.findAll();
    }
    public JournalEntry getJournalById(ObjectId id){
        return journalEntryRepository.findById(id).orElse(null);
    }
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}
