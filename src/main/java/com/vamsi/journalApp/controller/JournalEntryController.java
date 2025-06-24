package com.vamsi.journalApp.controller;


import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.service.JournalEntryService;
import com.vamsi.journalApp.service.UserEntryService;
import com.vamsi.journalApp.util.CurrentUserProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private CurrentUserProvider currentUser;
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/get-all-journals")
    public ResponseEntity<List<JournalEntry>> getAll(){
        String username = currentUser.getUsername();
        List<JournalEntry> all = journalEntryService.getAllJournals(username);
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id){
        String username = currentUser.getUsername();
        JournalEntry journal = journalEntryService.getJournalById(username,id);
        if(journal!=null){
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try{
            String username = currentUser.getUsername();
            myEntry.setTimestamp(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id){
        String username = currentUser.getUsername();
        journalEntryService.deleteById(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id,@RequestBody JournalEntry updatedEntry){
        String username = currentUser.getUsername();
        JournalEntry old = journalEntryService.getJournalById(username,id);
        if(old!=null){
            old.setTitle(updatedEntry.getTitle()!=null && !updatedEntry.getTitle().equals("")?updatedEntry.getTitle():old.getTitle());
            old.setContent(updatedEntry.getContent()!=null && !updatedEntry.getContent().equals("")?updatedEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
