package com.vamsi.journalApp.controller;


import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.repository.UserEntryRepository;
import com.vamsi.journalApp.service.UserEntryService;
import com.vamsi.journalApp.util.CurrentUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private CurrentUserProvider currentUserProvider;
    @Autowired
    private UserEntryRepository userEntryRepository;
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

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User updatedEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
        User user = userEntryService.findByUsername(username);
        if(user!=null){
            user.setUsername(updatedEntry.getUsername()!=null && !updatedEntry.getUsername().equals("")?updatedEntry.getUsername():user.getUsername());
            user.setPassword(!updatedEntry.getPassword().equals("")?updatedEntry.getPassword():user.getPassword());
            userEntryService.saveEntry(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUserById(){
        userEntryRepository.deleteByUsername(currentUserProvider.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
