package com.vamsi.journalApp.service;


import com.vamsi.journalApp.entity.User;
import com.vamsi.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Test
    public void testFindByUsername(){
        assertNotNull(userEntryService.findByUsername("vamsi"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
