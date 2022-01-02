package com.kayson.trackr.transaction;

import com.kayson.trackr.auth.AuthService;
import com.kayson.trackr.exception.NoSuchElementFoundException;
import com.kayson.trackr.transaction.dto.AddTransactionDTO;
import com.kayson.trackr.transaction.dto.TransactionDTO;
import com.kayson.trackr.wallet.Wallet;
import com.kayson.trackr.wallet.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public TransactionDTO addTransaction(@Valid @RequestBody AddTransactionDTO dto) {

        Wallet wallet = walletRepository.findByWalletByUser(dto.getWalletName(), this.authService.getAuthUser()).orElseThrow(() -> {
            throw new NoSuchElementFoundException(String.format("%s wallet not found for user", dto.getWalletName()));
        });

        Transaction transaction = this.transactionService.addTransaction(wallet, dto);
        return new TransactionDTO(transaction);
    }
}
