package com.bookshelf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.BookTags;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.BookSearchRepository;
import com.bookshelf.test.util.TestBooksFactory;
import com.bookshelf.util.BookCSVGenerator;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @Mock private BookRepository bookRepository;

  @Mock private BookCSVGenerator csvGenerator;

  @Mock private BookSearchRepository bookSearchRepository;

  @InjectMocks private BookService bookService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    Mockito.mockitoSession().initMocks(this).strictness(Strictness.LENIENT).startMocking();
  }

  @Test
  public void findAllBooksTest() {
    List<BookTags> bookTags = TestBooksFactory.getBookTags();
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(bookRepository);
    bookList.forEach(e -> e.setTags(bookTags));
    List<Book> result = bookService.findAllBooks();

    assertEquals(3, result.size());
  }

  @Test
  public void findBookByIdTest() {
    final long id = 0l;

    List<BookTags> bookTags = TestBooksFactory.getBookTags();
    Book book = TestBooksFactory.getBook(bookTags, 0);

    // when
    when(bookRepository.findById(id)).thenReturn(Optional.of(book));
    when(bookSearchRepository.getBookTagsByIsbn(id)).thenReturn(bookTags);
    Book result = bookService.findBookById(id).get();

    TestBooksFactory.assertBook(result, 0);
  }

  @Test
  public void addBookTest() {
    List<BookTags> bookTags = TestBooksFactory.getBookTags();
    Book book = TestBooksFactory.getBook(bookTags, 0);

    // when
    when(bookRepository.save(book)).thenReturn(book);
    when(bookSearchRepository.insertBookTags(bookTags)).thenReturn(null);
    // when(bookSearchRepository.getBookTagsByIsbn(book.getIsbn())).thenReturn(bookTags);
    Book result = bookService.addBook(book);
    TestBooksFactory.assertBook(result, 0);
  }

  @Test
  public void updateBookTest() {
    List<BookTags> bookTags = TestBooksFactory.getBookTags();
    Book book = TestBooksFactory.getBook(bookTags, 0);

    // when
    when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.of(book));
    when(bookRepository.save(book)).thenReturn(book);
    when(bookSearchRepository.insertBookTags(bookTags)).thenReturn(null);
    // when(bookSearchRepository.getBookTagsByIsbn(book.getIsbn())).thenReturn(bookTags);
    Book result = bookService.updateBook(book).get();
    TestBooksFactory.assertBook(result, 0);
  }

  @Test
  public void deleteBookTest() {
    List<BookTags> bookTags = TestBooksFactory.getBookTags();
    Book book = TestBooksFactory.getBook(bookTags, 12);

    // when
    when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.of(book));
    doNothing().when(bookRepository).delete(book);
    when(bookSearchRepository.deleteBookTagsWithIsbns(book.getIsbn())).thenReturn(1);
    boolean result = bookService.deleteBook(book.getIsbn());
    assertTrue(result);
  }

  @Test
  public void downloadBooksTest() {
    TestBooksFactory.setupFindAllBooks(bookRepository);
    ByteArrayInputStream in = bookService.downloadBooks();
    assertNull(in);
  }
}
