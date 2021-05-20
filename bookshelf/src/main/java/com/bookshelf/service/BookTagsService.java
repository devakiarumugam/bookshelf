package com.bookshelf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.domain.BookTags;
import com.bookshelf.exception.ResourceNotFoundException;
import com.bookshelf.repository.BookTagsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookTagsService {

  @Autowired private BookTagsRepository bookTagsRepository;

  public List<BookTags> findAllBookTags() {
    log.info("Start finding All Book Tags.");
    List<BookTags> books = bookTagsRepository.findAll();
    log.info("Completed finding All Book Tags.");
    return books;
  }

  public BookTags findBookTagsById(BookTags bookTagsRequest) {
    log.info("Start finding one book with ID : " + bookTagsRequest);
    BookTags bookTag =
        bookTagsRepository
            .findById(bookTagsRequest)
            .orElseThrow(() -> new ResourceNotFoundException("BookTags ID", bookTagsRequest));
    log.info("Completed finding one book with ID : " + bookTagsRequest);
    return bookTag;
  }

  public BookTags save(BookTags bookTagsRequest) {
    log.info("Start saving one book with ID : " + bookTagsRequest);
    BookTags bookTag = bookTagsRepository.save(bookTagsRequest);
    log.info("Completed saving one book with ID : " + bookTagsRequest);
    return bookTag;
  }

  public List<BookTags> saveAll(List<BookTags> bookTagsRequests) {
    log.info("Start saving All given tags.");
    List<BookTags> books = bookTagsRepository.saveAll(bookTagsRequests);
    log.info("Completed saving All given tags.");
    return books;
  }

  public Boolean delete(BookTags bookTagsRequest) {
    log.info("Start deleting one book with ID : " + bookTagsRequest);
    bookTagsRepository.delete(bookTagsRequest);
    log.info("Completed deleting one book with ID : " + bookTagsRequest);
    return true;
  }

  public Boolean deleteAll(List<BookTags> bookTagsRequests) {
    log.info("Start deleting All given tags.");
    bookTagsRepository.deleteAll(bookTagsRequests);
    log.info("Completed deleting All given tags.");
    return true;
  }

  public Boolean deleteAll() {
    log.info("Start Truncating Table.");
    bookTagsRepository.deleteAll();
    log.info("Completed Truncating Table.");
    return true;
  }
}
