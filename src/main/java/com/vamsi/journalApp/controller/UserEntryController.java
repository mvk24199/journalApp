package com.vamsi.journalApp.controller;


import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAll(){
        List<User> all = userEntryService.getAllUsers();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/get/{username}")
    public ResponseEntity<User> getJournalById(@PathVariable String username){
        User user = userEntryService.findByUsername(username);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createEntry(@RequestBody User myEntry){
        try{
            userEntryService.saveNewUser( myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }
    @PutMapping("/update/{name}")
    public ResponseEntity<User> updateUser(@PathVariable String name, @RequestBody User updatedEntry){
        User user = userEntryService.findByUsername(name);
        if(user!=null){
            user.setUsername(updatedEntry.getUsername()!=null && !updatedEntry.getUsername().equals("")?updatedEntry.getUsername():user.getUsername());
            user.setPassword(!updatedEntry.getPassword().equals("")?updatedEntry.getPassword():user.getPassword());
            userEntryService.saveEntry(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
