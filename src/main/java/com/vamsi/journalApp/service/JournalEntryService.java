package com.vamsi.journalApp.service;

import com.vamsi.journalApp.controller.UserEntryController;
import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    public void saveEntry(JournalEntry journalEntry,String username){
        try{
            User current = userEntryService.findByUsername(username);
            journalEntry.setTimestamp(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            current.getJournalEntries().add(saved);
            userEntryService.saveEntry(current);
        }catch(Exception e){
            logger.info("Exception: "+e);
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the journal");
        }

    }
    public List<JournalEntry> getAllJournals(String username){
        return userEntryService.findByUsername(username).getJournalEntries();
    }
    public JournalEntry getJournalById(String username,ObjectId id){
        User user = userEntryService.findByUsername(username);
        return journalEntryRepository.findById(id).orElse(null);
    }
    @Transactional
    public void deleteById(ObjectId id,String username){
        User user = userEntryService.findByUsername(username);
        boolean removed = user.getJournalEntries().removeIf(x ->
                x.getId().toHexString().equals(id.toHexString())
        );
        if (removed) {
            userEntryService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Journal entry not found for given ID");
        }
    }

    public void saveEntry(JournalEntry old) {
        journalEntryRepository.save(old);
    }
}
