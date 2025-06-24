package com.vamsi.journalApp.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {
    public String getUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.isAuthenticated()){
            return auth.getName();
        }
        return null;
    }
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
