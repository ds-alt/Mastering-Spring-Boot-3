package com.book.mastering_spring_boot_3.controller;

import com.book.mastering_spring_boot_3.dto.BookDTO;
import com.book.mastering_spring_boot_3.mapper.BookMapper;
import com.book.mastering_spring_boot_3.model.Book;
import com.book.mastering_spring_boot_3.service.BookService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book saved = bookService.save(book);
        return ResponseEntity.ok(BookMapper.toDTO(saved));
    }

    // READ All
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ One
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(BookMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO updatedDTO) {
        return bookService.getBookById(id)
                .map(book -> {
                    book.setTitle(updatedDTO.getTitle());
                    book.setAuthor(updatedDTO.getAuthor());
                    book.setPrice(updatedDTO.getPrice());
                    Book saved = bookService.save(book);
                    return ResponseEntity.ok(BookMapper.toDTO(saved));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.getBookById(id).isPresent()) {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/paged") // HTTP GET барање на патеката /books/paged (зависно од @RequestMapping во
                          // контролерот)
    public Page<BookDTO> getBooksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.getBooksPage(pageable)
                .map(BookMapper::toDTO);
    }

    @GetMapping("/books/paged") // Обработка на HTTP GET барање на URL патеката /books/paged
    public Page<BookDTO> getPagedBooks(
            @RequestParam(defaultValue = "0") int page, // Параметар за број на страна, по дифолт е 0 (прва страна)
            @RequestParam(defaultValue = "5") int size, // Параметар за број на записи по страна, по дифолт 5
            @RequestParam(defaultValue = "title") String sortBy) { // Параметар за полето по кое ќе се сортира, по
                                                                   // дифолт е "title"

        // Создавање на Pageable објект кој ја дефинира пагинацијата и сортирањето
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        // Повик на сервисот за да се добие пагинирана листа на Book ентитети,
        // која потоа се мапира од Book во BookDTO со помош на BookMapper.toDTO()
        return bookService.getBooksPage(pageable).map(BookMapper::toDTO);
    }

}
