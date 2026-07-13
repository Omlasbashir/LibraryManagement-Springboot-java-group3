package com.library.library_management.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private Integer categoryId;

    @Column(name = "categoryname", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "createddate", insertable = false, updatable = false)
    private LocalDateTime createdDate;
}