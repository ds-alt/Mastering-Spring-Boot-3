package com.book.mastering_spring_boot_3.controller;

import com.book.mastering_spring_boot_3.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/booklist")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "booklist"; // Thymeleaf template named booklist.html
    }
}
