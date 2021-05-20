package com.bookshelf.controller;

import com.bookshelf.domain.Book;
import com.bookshelf.service.BookSearchService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Book Search API")
@RestController
@RequestMapping("/api/bookSearch")
public class BookSearchController {

  @Autowired private BookSearchService bookSearchService;

  @GetMapping("/findByTitleStartsWith")
  public ResponseEntity<List<Book>> findByTitleStartsWith(
      @RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleStartsWith(title));
  }

  @GetMapping("/findByTitleLike")
  public ResponseEntity<List<Book>> findByTitleLike(@RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleLike(title));
  }

  @GetMapping("/findByTitleEndsWith")
  public ResponseEntity<List<Book>> findByTitleEndsWith(
      @RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleEndsWith(title));
  }

  @GetMapping("/findByTitleStartsWithIgnoreCase")
  public ResponseEntity<List<Book>> findByTitleStartsWithIgnoreCase(
      @RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleStartsWithIgnoreCase(title));
  }

  @GetMapping("/findByTitleLikeIgnoreCase")
  public ResponseEntity<List<Book>> findByTitleLikeIgnoreCase(
      @RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleLikeIgnoreCase(title));
  }

  @GetMapping("/findByTitleEndsWithIgnoreCase")
  public ResponseEntity<List<Book>> findByTitleEndsWithIgnoreCase(
      @RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleEndsWithIgnoreCase(title));
  }

  @GetMapping("/findByTitleNotLike")
  public ResponseEntity<List<Book>> findByTitleNotLike(
      @RequestParam(name = "search") String title) {
    return createResponseEntity(bookSearchService.findByTitleNotLike(title));
  }

  @GetMapping("/findByAuthorStartsWith")
  public ResponseEntity<List<Book>> findByAuthorStartsWith(
      @RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorStartsWith(author));
  }

  @GetMapping("/findByAuthorLike")
  public ResponseEntity<List<Book>> findByAuthorLike(@RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorLike(author));
  }

  @GetMapping("/findByAuthorEndsWith")
  public ResponseEntity<List<Book>> findByAuthorEndsWith(
      @RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorEndsWith(author));
  }

  @GetMapping("/findByAuthorStartsWithIgnoreCase")
  public ResponseEntity<List<Book>> findByAuthorStartsWithIgnoreCase(
      @RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorStartsWithIgnoreCase(author));
  }

  @GetMapping("/findByAuthorLikeIgnoreCase")
  public ResponseEntity<List<Book>> findByAuthorLikeIgnoreCase(
      @RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorLikeIgnoreCase(author));
  }

  @GetMapping("/findByAuthorEndsWithIgnoreCase")
  public ResponseEntity<List<Book>> findByAuthorEndsWithIgnoreCase(
      @RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorEndsWithIgnoreCase(author));
  }

  @GetMapping("/findByAuthorNotLike")
  public ResponseEntity<List<Book>> findByAuthorNotLike(
      @RequestParam(name = "search") String author) {
    return createResponseEntity(bookSearchService.findByAuthorNotLike(author));
  }

  @GetMapping("/findBooksWithTag")
  public ResponseEntity<List<Book>> findBooksWithTag(@RequestParam(name = "search") String tag) {
    return createResponseEntity(bookSearchService.getBooksWithTag(tag));
  }

  @PostMapping("/findBooksInTag")
  public ResponseEntity<List<Book>> findBooksInTag(@RequestBody List<String> tags) {
    return createResponseEntity(bookSearchService.getBooksInTag(tags));
  }

  private ResponseEntity<List<Book>> createResponseEntity(List<Book> result) {
    if (result.size() > 0) {
      return new ResponseEntity<>(result, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
  }
}
