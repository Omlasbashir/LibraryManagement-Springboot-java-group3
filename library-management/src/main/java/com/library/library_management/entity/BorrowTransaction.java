package com.library.library_management.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "borrowtransactions")
@Data
public class BorrowTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowid")
    private Integer borrowId;

    @ManyToOne
    @JoinColumn(name = "memberid", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookid", nullable = false)
    private Book book;

    @Column(name = "borrowdate", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "duedate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "returndate")
    private LocalDate returnDate;

    @Column(name = "status", length = 20)
    private String status = "Borrowed"; // Borrowed ama Returned
}