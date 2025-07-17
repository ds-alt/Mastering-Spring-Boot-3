package com.book.mastering_spring_boot_3.repository;

import com.book.mastering_spring_boot_3.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
