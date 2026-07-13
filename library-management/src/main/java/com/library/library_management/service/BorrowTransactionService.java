package com.library.library_management.service;

import com.library.library_management.entity.BorrowTransaction;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.repository.BorrowTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowTransactionService {

    @Autowired
    private BorrowTransactionRepository transactionRepository;

    public List<BorrowTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public BorrowTransaction borrowBook(BorrowTransaction transaction) {
        transaction.setBorrowDate(LocalDate.now());
        transaction.setStatus("BORROWED");
        return transactionRepository.save(transaction);
    }

    public BorrowTransaction returnBook(Integer id) {
        BorrowTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction-kaan lama helin!"));
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("RETURNED");
        return transactionRepository.save(transaction);
    }
}