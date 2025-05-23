package com.example.phoenix.service;

import com.example.phoenix.context.exception.NotFoundException;
import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.domain.BookEntity;
import com.example.phoenix.model.mapper.BookMapper;
import com.example.phoenix.model.request.BookRequest;
import com.example.phoenix.model.response.BookResponse;
import com.example.phoenix.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class BookService {
  private final BookRepository bookRepository;

  public BookResponse createBook(final BookRequest request) {
    log.debug("Create book with request: {}", request);
    BookEntity book = BookMapper.INSTANCE.toEntity(request);
    return BookMapper.INSTANCE.toResponse(bookRepository.save(book));
  }

  public BookResponse findById(UUID id) {
    log.debug("Find book by id: {}", id);
    BookEntity book = bookRepository.findByIdAndDeletedIsFalse(id);
    return BookMapper.INSTANCE.toResponse(book);
  }

  public BookResponse updateById(UUID id, BookRequest request) {
    log.debug("Update book by id: {} with request: {}", id, request);
    BookEntity book = bookRepository.findByIdAndDeletedIsFalse(id);
    if (book == null) {
      throw new NotFoundException(ErrorCode.BOOK_NOT_FOUND, "Book not found");
    }
    BookEntity updatedBook = BookMapper.INSTANCE.toUpdate(book, request);
    return BookMapper.INSTANCE.toResponse(bookRepository.save(updatedBook));
  }

  public List<BookResponse> findAllBooks() {
    log.debug("Find all books");
    return bookRepository.findAllByDeletedFalse().stream()
        .map(BookMapper.INSTANCE::toResponse)
        .toList();
  }

  public Page<BookResponse> findAll(Pageable pageable) {
    log.debug("Find all books with pagination: {}", pageable);
    return bookRepository.findAllByDeletedFalse(pageable).map(BookMapper.INSTANCE::toResponse);
  }

  public void deleteById(UUID id) {
    log.debug("Delete book by id: {}", id);
    bookRepository.deleteById(id);
  }
}
