package com.kayson.trackr.auth;

import com.kayson.trackr.exception.NoSuchElementFoundException;
import com.kayson.trackr.user.User;
import com.kayson.trackr.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AuthService {
    UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getAuthUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("searching for user {}", userDetails.getUsername());
        return userRepository.findById(UUID.fromString(userDetails.getUsername())).orElseThrow(() -> new NoSuchElementFoundException("user does not exist"));
    }
}
