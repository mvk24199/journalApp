package com.vamsi.journalApp.controller;


import com.vamsi.journalApp.entity.JournalEntry;
import com.vamsi.journalApp.entity.UserEntry;
import com.vamsi.journalApp.service.JournalEntryService;
import com.vamsi.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserEntry>> getAll(){

        List<UserEntry> all = userEntryService.getAllUsers();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get/{username}")
    public ResponseEntity<UserEntry> getJournalById(@PathVariable String username){
        UserEntry user = userEntryService.findByUsername(username);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<UserEntry> createEntry(@RequestBody UserEntry myEntry){
        try{
            userEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }
    @PutMapping("/update/{name}")
    public ResponseEntity<UserEntry> updateUser(@PathVariable String name, @RequestBody UserEntry updatedEntry){
        UserEntry user = userEntryService.findByUsername(name);
        if(user!=null){
            user.setUsername(updatedEntry.getUsername()!=null && !updatedEntry.getUsername().equals("")?updatedEntry.getUsername():user.getUsername());
            user.setPassword(!updatedEntry.getPassword().equals("")?updatedEntry.getPassword():user.getPassword());
            userEntryService.saveEntry(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
