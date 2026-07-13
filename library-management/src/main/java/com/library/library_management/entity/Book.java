package com.library.library_management.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid")
    private Integer bookId;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "isbn", unique = true, length = 50)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;
}