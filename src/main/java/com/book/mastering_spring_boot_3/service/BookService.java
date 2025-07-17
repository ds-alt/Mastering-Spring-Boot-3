package com.book.mastering_spring_boot_3.service;

import com.book.mastering_spring_boot_3.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book save(Book book);

    void deleteBook(Long id);

    Page<Book> getBooksPage(Pageable pageable);
}
