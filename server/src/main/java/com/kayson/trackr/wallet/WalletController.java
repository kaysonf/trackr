package com.kayson.trackr.wallet;

import com.kayson.trackr.auth.AuthService;
import com.kayson.trackr.user.User;
import com.kayson.trackr.wallet.dto.CreateWalletDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;
    private final AuthService authService;

    @Autowired
    public WalletController(WalletService walletService, AuthService authService) {
        this.walletService = walletService;
        this.authService = authService;
    }

    @GetMapping
    public List<Wallet> getAllWallets() {
        User user = authService.getAuthUser();
        return walletService.getAllWallets(user);
    }

    @PostMapping
    public Wallet createWallet(@Valid @RequestBody CreateWalletDTO createWalletDTO) {
        User user = authService.getAuthUser();

        return walletService.createWallet(user, createWalletDTO);
    }
}
