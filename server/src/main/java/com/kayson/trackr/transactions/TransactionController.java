package com.kayson.trackr.transactions;

import com.kayson.trackr.auth.AuthService;
import com.kayson.trackr.transactions.dto.AddTransactionDTO;
import com.kayson.trackr.wallet.Wallet;
import com.kayson.trackr.wallet.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final WalletRepository walletRepository;
    private final TransactionService transactionService;
    private final AuthService authService;

    @Autowired
    public TransactionController(WalletRepository walletRepository, TransactionService transactionService, AuthService authService) {
        this.walletRepository = walletRepository;
        this.transactionService = transactionService;
        this.authService = authService;
    }

    @PostMapping
    public Transaction addTransaction(@Valid @RequestBody AddTransactionDTO dto) {

        Wallet wallet = walletRepository.findByWalletByUser(dto.getWalletName(), this.authService.getAuthUser()).orElseThrow(() -> {
            throw new NoSuchElementException(String.format("%s wallet not found for user", dto.getWalletName()));
        });

        return this.transactionService.addTransaction(wallet, dto);
    }
}
