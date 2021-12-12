package com.kayson.trackr.auth;

import java.util.ArrayList;

import com.kayson.trackr.user.User;
import com.kayson.trackr.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String handle) throws UsernameNotFoundException {
        User user = userService.getUserByHandle(handle);

        return new org.springframework.security.core.userdetails.User(user.getHandle(),
                user.getPassword(),
                new ArrayList<>());
    }
}
