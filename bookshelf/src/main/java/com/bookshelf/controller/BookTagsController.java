package com.bookshelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookshelf.domain.BookTags;
import com.bookshelf.service.BookTagsService;

import io.swagger.annotations.Api;

@Api(tags = "Book Tag API")
@RestController
@RequestMapping("/api/bookTags")
public class BookTagsController {

  @Autowired private BookTagsService bookTagsService;

  @GetMapping
  public ResponseEntity<List<BookTags>> getBookTags() {
    return new ResponseEntity<>(bookTagsService.findAllBookTags(), HttpStatus.OK);
  }

  @GetMapping("/get")
  public ResponseEntity<BookTags> getBookTagsById(
      @RequestParam(name = "tag") String bookTagsId, @RequestParam(name = "isbn") Long isbn) {
    return new ResponseEntity<>(
        bookTagsService.findBookTagsById(new BookTags(isbn, bookTagsId)), HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<BookTags> create(@RequestBody BookTags bookTagsRequest) {
    return new ResponseEntity<>(bookTagsService.save(bookTagsRequest), HttpStatus.CREATED);
  }

  @PostMapping("/addAll")
  public ResponseEntity<List<BookTags>> addAll(@RequestBody List<BookTags> bookTagsRequests) {
    return new ResponseEntity<>(bookTagsService.saveAll(bookTagsRequests), HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<BookTags> update(@RequestBody BookTags bookTagsRequest) {
    return new ResponseEntity<>(bookTagsService.save(bookTagsRequest), HttpStatus.CREATED);
  }

  @PutMapping("/updateAll")
  public ResponseEntity<List<BookTags>> updateAll(@RequestBody List<BookTags> bookTagsRequests) {
    return new ResponseEntity<>(bookTagsService.saveAll(bookTagsRequests), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> delete(@RequestBody BookTags bookTagsRequest) {
    bookTagsService.delete(bookTagsRequest);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @DeleteMapping("/deleteAll")
  public ResponseEntity<Void> deleteAll(@RequestBody List<BookTags> bookTagsRequests) {
    bookTagsService.deleteAll(bookTagsRequests);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }
}
