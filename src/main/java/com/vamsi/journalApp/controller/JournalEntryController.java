package com.vamsi.journalApp.controller;


import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/{user}/get-all-journals")
    public ResponseEntity<List<JournalEntry>> getAll(@PathVariable String user){
        List<JournalEntry> all = journalEntryService.getAllJournals(user);
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get/{username}/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable String username,@PathVariable ObjectId id){
        JournalEntry journal = journalEntryService.getJournalById(username,id);
        if(journal!=null){
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String username){
        try{
            myEntry.setTimestamp(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }
    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable String username,@PathVariable ObjectId id){
        journalEntryService.deleteById(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{username}/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable String username,@PathVariable ObjectId id,@RequestBody JournalEntry updatedEntry){
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
