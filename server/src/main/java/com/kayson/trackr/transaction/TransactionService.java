package com.kayson.trackr.transaction;

import com.kayson.trackr.category.Category;
import com.kayson.trackr.category.CategoryRepository;
import com.kayson.trackr.category.CategoryService;
import com.kayson.trackr.transaction.dto.AddTransactionDTO;
import com.kayson.trackr.wallet.Wallet;
import com.kayson.trackr.wallet.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final WalletService walletService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, CategoryService categoryService, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.walletService = walletService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Transaction addTransaction(Wallet wallet, AddTransactionDTO dto) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(dto.getCategoryName());

        if (optionalCategory.isEmpty()) {
            log.info("creating new category {}", dto.getCategoryName());
            optionalCategory = Optional.of(categoryService.createCategory(dto.getCategoryName()));
        }

        Category category = optionalCategory.get();

        Transaction transaction = new Transaction(wallet, dto.getDate(), dto.getAmount(), category);

        walletService.updateWalletAmount(wallet, transaction.getAmount());

        return transactionRepository.save(transaction);
    }
}
