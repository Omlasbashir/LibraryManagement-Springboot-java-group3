package com.library.library_management.controller;

import com.library.library_management.entity.BorrowTransaction;
import com.library.library_management.service.BorrowTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/BorrowTransactions")
public class BorrowTransactionController {


    @Autowired
    private BorrowTransactionService transactionService;


    // GET ALL
    @GetMapping
    public ResponseEntity<List<BorrowTransaction>> getAllTransactions() {

        return ResponseEntity.ok(
                transactionService.getAllTransactions()
        );
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BorrowTransaction> getTransactionById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                transactionService.getTransactionById(id)
        );
    }


    // CREATE BORROW
    @PostMapping
    public ResponseEntity<BorrowTransaction> createTransaction(
            @RequestBody BorrowTransaction transaction
    ) {

        return ResponseEntity.ok(
                transactionService.borrowBook(transaction)
        );
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BorrowTransaction> updateTransaction(
            @PathVariable Integer id,
            @RequestBody BorrowTransaction transaction
    ) {

        return ResponseEntity.ok(
                transactionService.updateTransaction(id, transaction)
        );
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(
            @PathVariable Integer id
    ) {

        transactionService.deleteTransaction(id);

        return ResponseEntity.ok(
                "Borrow transaction deleted successfully"
        );
    }


    // RETURN BOOK
    @PostMapping("/return/{id}")
    public ResponseEntity<BorrowTransaction> returnBook(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                transactionService.returnBook(id)
        );
    }
}