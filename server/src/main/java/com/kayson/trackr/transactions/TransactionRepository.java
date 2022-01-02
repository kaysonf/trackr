package com.kayson.trackr.transactions;

import com.kayson.trackr.validators.UUID;
import com.kayson.trackr.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("SELECT t FROM Transaction t WHERE t.wallet = ?1")
    Optional<List<Transaction>> findByWallet(Wallet wallet);
}
