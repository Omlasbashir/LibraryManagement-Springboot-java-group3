package com.library.library_management.controller;

import com.library.library_management.entity.BorrowTransaction;
import com.library.library_management.service.BorrowTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
//@CrossOrigin(origins = "*") // Tan waxay ka hortagtaa ciladaha CORS hadhow
public class BorrowTransactionController {

    @Autowired
    private BorrowTransactionService transactionService;

    @GetMapping
    public List<BorrowTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }


    @PostMapping("/borrow")
    public ResponseEntity<BorrowTransaction> borrowBook(@RequestBody BorrowTransaction transaction) {
        return ResponseEntity.ok(transactionService.borrowBook(transaction));
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<BorrowTransaction> returnBook(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionService.returnBook(id));
    }
}