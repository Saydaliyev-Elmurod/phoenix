package com.example.phoenix.service;

import com.example.phoenix.model.BookRequest;
import com.example.phoenix.model.BookResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class BookService {
  public BookResponse createBook(BookRequest request) {
    return null;
  }

  public BookResponse findById(String id) {
    return null;
  }

  public List<BookResponse> findAllBooks() {
    return null;
  }

  public Page<BookResponse> findAll(Pageable pageable) {
    return null;
  }
}
