package com.zpi.bmarket.bmarket.services;

import com.zpi.bmarket.bmarket.domain.User;
import com.zpi.bmarket.bmarket.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class UsersService {
    public static User getUser(Long id, UserRepository userRepository){
        if(id == null)return null;
        return userRepository.findById(id).orElse(null);
    }
    public static User getUser(HttpSession session, UserRepository userRepository){
        return getUser(getUserIdFromSession(session),userRepository);
    }
    public static Long getUserIdFromSession(HttpSession session){
        return (Long) session.getAttribute("userId");
    }
}
