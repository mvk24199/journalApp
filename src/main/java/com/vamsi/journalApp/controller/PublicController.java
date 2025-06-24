package com.vamsi.journalApp.controller;

import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserEntryService userEntryService;

    @PostMapping("/create-user")
    public ResponseEntity<User> createEntry(@RequestBody User myEntry){
        try{
            myEntry.setRoles(List.of("USER"));
            userEntryService.saveNewUser( myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
