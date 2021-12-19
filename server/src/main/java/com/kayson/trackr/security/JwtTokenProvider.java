package com.kayson.trackr.security;

import com.kayson.trackr.user.User;
import com.kayson.trackr.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private String secret = "some_secret";
    private long validFor = 360_000; // 1hr

    private final AppUserDetailsService appUserDetailsService;
    private final UserRepository userRepository;

    public JwtTokenProvider(AppUserDetailsService appUserDetailsService, UserRepository userRepository) {
        this.appUserDetailsService = appUserDetailsService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createTokenFromUserId(UUID userId) {

        Claims claims = Jwts.claims().setSubject(userId.toString());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validFor);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        User user = userRepository.findById(UUID.fromString(getUserId(token))).orElseThrow();
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(user.getHandle());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

}
