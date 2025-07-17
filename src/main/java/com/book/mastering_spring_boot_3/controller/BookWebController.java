package com.book.mastering_spring_boot_3.controller;

import com.book.mastering_spring_boot_3.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookWebController {
    private final BookService bookService;

    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/view")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // враќа Thymeleaf фајл books.html
    }
}
