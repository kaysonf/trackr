package com.kayson.trackr.transactions;

import com.kayson.trackr.transactions.dto.AddTransactionDTO;
import com.kayson.trackr.wallet.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    public Transaction addTransaction(Wallet wallet, AddTransactionDTO dto) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByName(dto.getCategoryName());

        if (optionalCategory.isEmpty()) {
            log.info("creating new category {}", dto.getCategoryName());
            optionalCategory = Optional.of(categoryService.createCategory(dto.getCategoryName()));
        }

        Category category = optionalCategory.get();

        Transaction transaction = new Transaction(wallet, dto.getDate(), dto.getAmount(), category);

        return transactionRepository.save(transaction);
    }
}
