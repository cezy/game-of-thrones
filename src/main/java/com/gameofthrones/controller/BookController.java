package com.gameofthrones.controller;

import com.gameofthrones.dto.BookDto;
import com.gameofthrones.mapper.BookMapper;
import com.gameofthrones.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/books")
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> findBooks() {
        return ResponseEntity.ok(
                bookService.findBooks()
                        .stream()
                        .map(bookMapper::to)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBook(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                bookMapper.to(bookService.findBook(id)));
    }
}
