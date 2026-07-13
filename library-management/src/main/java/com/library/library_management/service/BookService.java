package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buuggan lagama helin maktabadda!"));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setQuantity(bookDetails.getQuantity());
        book.setCategory(bookDetails.getCategory());
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}