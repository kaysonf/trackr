package com.kayson.trackr.wallet;

import com.kayson.trackr.exceptions.AlreadyExistsException;
import com.kayson.trackr.user.User;
import com.kayson.trackr.wallet.dto.CreateWalletDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<Wallet> getAllWallets(User user) {
        return walletRepository.findByUser(user).orElse(new ArrayList<>());
    }

    public Wallet createWallet(User user, CreateWalletDTO createWalletDTO) {
        String walletName = createWalletDTO.getName();

        if (walletRepository.findByWalletByUser(walletName, user).isPresent()) {
            throw new AlreadyExistsException(String.format("The wallet %s already exists", walletName));
        }

        Wallet wallet = new Wallet(user, walletName);

        return walletRepository.save(wallet);
    }
}
