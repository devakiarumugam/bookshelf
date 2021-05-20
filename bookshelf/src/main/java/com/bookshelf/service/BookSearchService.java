package com.bookshelf.service;

import com.bookshelf.domain.Book;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.BookSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookSearchService {

  private static final String START_MESSAGE_TITLE =
      "BSF:I:BookSearch - Finding List of matching books with given title : '%s'";

  private static final String START_MESSAGE_AUTHOR =
      "BSF:I:BookSearch - Finding List of matching books with given author : '%s'";

  private static final String START_MESSAGE_TAG =
      "BSF:I:BookSearch - Finding List of matching books with given tag : '%s'";

  private static final String COMPLETED_MESSAGE_TITLE =
      "BSF:I:BookSearch - Completed Finding List of matching books with given title : '%s'";

  private static final String COMPLETED_MESSAGE_AUTHOR =
      "BSF:I:BookSearch - Completed Finding List of matching books with given author : '%s'";

  private static final String COMPLETED_MESSAGE_TAG =
      "BSF:I:BookSearch - Completed Finding List of matching books with given tag : '%s'";

  @Autowired private BookRepository bookRepository;

  @Autowired private BookSearchRepository bookSearchRepository;

  public List<Book> findByTitleStartsWith(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleStartsWith(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByTitleLike(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleLike(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByTitleEndsWith(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleEndsWith(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByTitleStartsWithIgnoreCase(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleStartsWithIgnoreCase(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByTitleLikeIgnoreCase(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleLikeIgnoreCase(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByTitleEndsWithIgnoreCase(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleEndsWithIgnoreCase(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByTitleNotLike(String title) {
    log.info(String.format(START_MESSAGE_TITLE, title));
    List<Book> books = bookRepository.findByTitleNotLike(title);
    log.info(String.format(COMPLETED_MESSAGE_TITLE, title));
    return books;
  }

  public List<Book> findByAuthorStartsWith(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorStartsWith(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> findByAuthorLike(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorLike(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> findByAuthorEndsWith(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorEndsWith(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> findByAuthorStartsWithIgnoreCase(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorStartsWithIgnoreCase(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> findByAuthorLikeIgnoreCase(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorLikeIgnoreCase(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> findByAuthorEndsWithIgnoreCase(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorEndsWithIgnoreCase(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> findByAuthorNotLike(String author) {
    log.info(String.format(START_MESSAGE_AUTHOR, author));
    List<Book> books = bookRepository.findByAuthorNotLike(author);
    log.info(String.format(COMPLETED_MESSAGE_AUTHOR, author));
    return books;
  }

  public List<Book> getBooksWithTag(String tag) {
    log.info(String.format(START_MESSAGE_TAG, tag));
    List<Long> isbns = bookSearchRepository.getBookIsbnsWithTag(tag);
    List<Book> books = bookRepository.findAllById(isbns);
    log.info(String.format(COMPLETED_MESSAGE_TAG, tag));
    return books;
  }

  public List<Book> getBooksInTag(List<String> tags) {
    log.info(String.format(START_MESSAGE_TAG, tags));

    List<Long> isbns = bookSearchRepository.getBookIsbnsInTag(tags);
    List<Book> books = bookRepository.findAllById(isbns);
    log.info(String.format(COMPLETED_MESSAGE_TAG, tags));
    return books;
  }
}
