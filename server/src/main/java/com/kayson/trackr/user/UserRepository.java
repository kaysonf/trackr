package com.kayson.trackr.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.handle = ?1")
    Optional<User> findByHandle(String handle);

    @Query("SELECT u from User u WHERE u.email = ?1")
    Optional<User> findByEmail(String handle);
}
