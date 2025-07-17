package com.book.mastering_spring_boot_3;

import com.book.mastering_spring_boot_3.model.Book;
import com.book.mastering_spring_boot_3.repository.BookRepository;
import com.book.mastering_spring_boot_3.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService; // Тука сега е имплементацијата, не интерфејсот

    @Test
    void testGetBookById() {
        Book mockBook = new Book("Spring Boot", "Lucas Wiig", 29.99);
        mockBook.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        Optional<Book> result = bookService.getBookById(1L);
        assertTrue(result.isPresent());
        assertEquals("Spring Boot", result.get().getTitle());
    }
}
