package com.example.phoenix.api;

import com.example.phoenix.model.BookRequest;
import com.example.phoenix.model.BookResponse;
import com.example.phoenix.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @PostMapping
  public BookResponse create(@RequestBody BookRequest request) {
    return bookService.createBook(request);
  }

  @GetMapping("/{id}")
  public BookResponse findById(@PathVariable("id") final String id) {
    return bookService.findById(id);
  }

  @GetMapping("/all")
  public List<BookResponse> findAll() {
    return bookService.findAllBooks();
  }

  @GetMapping()
  public Page<BookResponse> findAll(Pageable pageable) {
    return bookService.findAll(pageable);
  }
}
