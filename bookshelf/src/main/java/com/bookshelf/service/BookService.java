package com.bookshelf.service;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.BookTags;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.BookSearchRepository;
import com.bookshelf.util.BookCSVGenerator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

  @Autowired private BookRepository bookRepository;

  @Autowired private BookCSVGenerator csvGenerator;

  @Autowired private BookSearchRepository bookSearchRepository;

  public List<Book> findAllBooks() {
    log.info("Start finding All Books");
    List<Book> books = bookRepository.findAll();
    books.forEach(book -> book.setTags(bookSearchRepository.getBookTagsByIsbn(book.getIsbn())));
    log.info("Completed finding All Books");
    return books;
  }

  public Optional<Book> findBookById(Long isbn) {
    log.info("Start finding one Book with Id : " + isbn);
    Optional<Book> book =
        bookRepository
            .findById(isbn)
            .map(
                book1 -> {
                  book1.setTags(bookSearchRepository.getBookTagsByIsbn(isbn));
                  return book1;
                });

    log.info("Completed finding one Book with Id : " + isbn);
    return book;
  }

  public Book addBook(Book bookRequest) {
    log.info("Start adding one Book : " + bookRequest);
    Book book = bookRepository.save(bookRequest);
    saveBookTags(bookRequest.getIsbn(), bookRequest.getTags(), book);
    log.info("Completed adding one Book : " + bookRequest);
    return book;
  }

  public Optional<Book> updateBook(Book bookRequest) {
    log.info("Start updating one Book : " + bookRequest);
    Optional<Book> bookResponse =
        bookRepository
            .findById(bookRequest.getIsbn())
            .map(
                book -> {
                  book.setIsbn(bookRequest.getIsbn());
                  book.setAuthor(bookRequest.getAuthor());
                  book.setTitle(bookRequest.getTitle());
                  book = bookRepository.save(book);
                  Optional.of(bookRequest.getTags())
                      .ifPresent(
                          tags ->
                              tags.stream().forEach(tag -> tag.setBookIsbn(bookRequest.getIsbn())));
                  saveBookTags(book.getIsbn(), bookRequest.getTags(), book);
                  return book;
                });
    log.info("Completed updating one Book : " + bookRequest);
    return bookResponse;
  }

  public boolean deleteBook(Long isbn) {
    log.info("Start deleting one Book with isbn: " + isbn);
    Optional<Book> book = bookRepository.findById(isbn);
    book.ifPresent(bookRepository::delete);
    book.map(
        book1 -> {
          bookSearchRepository.deleteBookTagsWithIsbns(book1.getIsbn());
          return book1;
        });

    log.info("Completed deleting one Book with isbn: " + isbn);
    return book.isPresent();
  }

  public ByteArrayInputStream downloadBooks() {
    log.info("Start downloading all books.");
    List<Book> books = bookRepository.findAll();
    books.forEach(book -> book.setTags(bookSearchRepository.getBookTagsByIsbn(book.getIsbn())));
    ByteArrayInputStream in = csvGenerator.booksToCsv(books);
    log.info("Completed downloading all books.");
    return in;
  }

  private boolean saveBookTags(long isbn, List<BookTags> bookTags, Book result) {
    bookSearchRepository.deleteBookTagsWithIsbns(isbn);
    bookSearchRepository.insertBookTags(bookTags);
    result.setTags(bookTags);
    return true;
  }
}
