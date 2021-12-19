package com.kayson.trackr.auth;

import com.kayson.trackr.security.JwtTokenProvider;
import com.kayson.trackr.user.User;
import com.kayson.trackr.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping
    public String login(@Valid @RequestBody AuthDTO authDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getHandle(), authDto.getPassword()));
        User user = userRepository.findByHandle(authDto.getHandle()).orElseThrow();
        return jwtTokenProvider.createTokenFromUserId(user.getId());
    }
}
