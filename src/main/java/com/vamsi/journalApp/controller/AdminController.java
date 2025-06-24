package com.vamsi.journalApp.controller;

import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> all = userEntryService.getAllUsers();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return null;
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userEntryService.saveAdmin(user);
    }
}
