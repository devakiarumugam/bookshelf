package com.bookshelf.controller;

import com.bookshelf.domain.Book;
import com.bookshelf.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Book Shelf API")
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired private BookService bookService;

  @GetMapping
  public ResponseEntity<List<Book>> getBooks() {
    return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> downloadBooks() {
    String filename = "books.csv";
    InputStreamResource file = new InputStreamResource(bookService.downloadBooks());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }

  @GetMapping("/{isbn}")
  public ResponseEntity<Book> getBookById(@PathVariable Long isbn) {
    Optional<Book> bookById = bookService.findBookById(isbn);
    return constructHttpResponse(bookById);
  }

  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book bookRequest) {
    return new ResponseEntity<>(bookService.addBook(bookRequest), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Book> update(@RequestBody Book bookRequest) {
    Optional<Book> updatedBook = bookService.updateBook(bookRequest);
    return constructHttpResponse(updatedBook);
  }

  private ResponseEntity<Book> constructHttpResponse(Optional<Book> updatedBook) {
    if (updatedBook.isPresent()) {
      return new ResponseEntity<>(updatedBook.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{isbn}")
  public ResponseEntity<Void> delete(@PathVariable Long isbn) {
    if (bookService.deleteBook(isbn)) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
  }
}
