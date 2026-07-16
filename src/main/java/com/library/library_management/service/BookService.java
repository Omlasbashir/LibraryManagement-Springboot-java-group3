package com.library.library_management.service;

import com.library.library_management.entity.Book;
import com.library.library_management.entity.Category;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.CategoryRepository;
import com.library.library_management.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buuggan lagama helin maktabadda!"));
    }

    public Book saveBook(Book book) {
        Category category = resolveCategory(book.getCategoryId());
        book.setCategory(category);
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, Book bookDetails) {
        Book book = getBookById(id);

        Category category = resolveCategory(bookDetails.getCategoryId());

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setQuantity(bookDetails.getQuantity());
        book.setCategory(category);

        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    private Category resolveCategory(Integer categoryId) {
        if (categoryId == null) {
            throw new ResourceNotFoundException("Fadlan dooro category!");
        }
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category-gan lagama helin!"));
    }
}