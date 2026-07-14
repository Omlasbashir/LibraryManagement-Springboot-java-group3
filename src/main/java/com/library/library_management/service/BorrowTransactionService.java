package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.entity.Member;
import com.library.library_management.entity.BorrowTransaction;
import com.library.library_management.exception.ResourceNotFoundException;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.MemberRepository;
import com.library.library_management.repository.BorrowTransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowTransactionService {

    @Autowired
    private BorrowTransactionRepository transactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;


    // GET ALL
    public List<BorrowTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    // GET BY ID
    public BorrowTransaction getTransactionById(Integer id) {

        return transactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found"));
    }


    // CREATE
    public BorrowTransaction borrowBook(BorrowTransaction transaction) {

        Member member = memberRepository.findById(
                transaction.getMember().getMemberId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));


        Book book = bookRepository.findById(
                transaction.getBook().getBookId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Book not found"));


        transaction.setMember(member);
        transaction.setBook(book);

        transaction.setBorrowDate(LocalDate.now());
        transaction.setStatus("Borrowed");


        return transactionRepository.save(transaction);
    }



    // UPDATE
    public BorrowTransaction updateTransaction(Integer id, BorrowTransaction transaction) {


        BorrowTransaction old = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found"));


        Member member = memberRepository.findById(
                transaction.getMember().getMemberId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Member not found"));


        Book book = bookRepository.findById(
                transaction.getBook().getBookId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Book not found"));


        old.setMember(member);
        old.setBook(book);
        old.setBorrowDate(transaction.getBorrowDate());
        old.setDueDate(transaction.getDueDate());
        old.setReturnDate(transaction.getReturnDate());
        old.setStatus(transaction.getStatus());


        return transactionRepository.save(old);
    }



    // DELETE
    public void deleteTransaction(Integer id) {

        BorrowTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found"));


        transactionRepository.delete(transaction);
    }



    // RETURN BOOK
    public BorrowTransaction returnBook(Integer id) {

        BorrowTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Transaction not found"));


        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("Returned");


        return transactionRepository.save(transaction);
    }
}