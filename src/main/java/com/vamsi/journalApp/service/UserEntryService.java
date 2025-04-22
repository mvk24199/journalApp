package com.vamsi.journalApp.service;

import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(User user){
        userEntryRepository.save(user);
    }
    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userEntryRepository.findAll();
    }
    public User getJournalById(ObjectId id){
        return userEntryRepository.findById(id).orElse(null);
    }
    public void deleteById(ObjectId id){userEntryRepository.deleteById(id);}
    public User findByUsername(String username){
        return userEntryRepository.findByUsername(username);
    }
}
