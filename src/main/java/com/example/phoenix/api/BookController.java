package com.example.phoenix.api;

import com.example.phoenix.model.request.BookRequest;
import com.example.phoenix.model.response.BookResponse;
import com.example.phoenix.service.BookService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public BookResponse create(@RequestBody BookRequest request) {
    return bookService.createBook(request);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public BookResponse findById(
      @PathVariable("id") final UUID id, @RequestBody BookRequest request) {
    return bookService.updateById(id, request);
  }

  @GetMapping("/{id}")
  public BookResponse findById(@PathVariable("id") final UUID id) {
    return bookService.findById(id);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") final UUID id) {
    bookService.deleteById(id);
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
