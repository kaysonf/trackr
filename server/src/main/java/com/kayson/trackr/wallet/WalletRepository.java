package com.kayson.trackr.wallet;


import com.kayson.trackr.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    @Query("SELECT w FROM Wallet w WHERE w.user = ?1")
    Optional<List<Wallet>> findByUser(User user);

    @Query("SELECT w FROM Wallet w WHERE w.name = ?1 and w.user = ?2")
    Optional<Wallet> findByWalletByUser(String name, User user);
}
