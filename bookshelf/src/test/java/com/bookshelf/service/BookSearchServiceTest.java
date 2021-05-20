package com.bookshelf.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookshelf.domain.Book;
import com.bookshelf.repository.BookRepository;
import com.bookshelf.repository.BookSearchRepository;
import com.bookshelf.test.util.TestBooksFactory;

@ExtendWith(MockitoExtension.class)
public class BookSearchServiceTest {

  private final String title = "Test";

  @Mock private BookRepository bookRepository;

  @Mock private BookSearchRepository bookSearchRepository;

  @InjectMocks private BookSearchService bookSearchService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findByTitleStartsWithTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleStartsWith(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleStartsWith(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByTitleLikeTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleLike(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleLike(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByTitleEndsWithTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleEndsWith(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleEndsWith(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByTitleStartsWithIgnoreCaseTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleStartsWithIgnoreCase(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleStartsWithIgnoreCase(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByTitleLikeIgnoreCaseTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleLikeIgnoreCase(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleLikeIgnoreCase(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByTitleEndsWithIgnoreCaseTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleEndsWithIgnoreCase(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleEndsWithIgnoreCase(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByTitleNotLikeTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByTitleNotLike(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByTitleNotLike(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorStartsWithTest() {

    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByAuthorStartsWith(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorStartsWith(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorLikeTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByAuthorLike(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorLike(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorEndsWithTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);

    // when
    when(bookRepository.findByAuthorEndsWith(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorEndsWith(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorStartsWithIgnoreCaseTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);
    // when
    when(bookRepository.findByAuthorStartsWithIgnoreCase(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorStartsWithIgnoreCase(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorLikeIgnoreCaseTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);
    // when
    when(bookRepository.findByAuthorLikeIgnoreCase(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorLikeIgnoreCase(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorEndsWithIgnoreCaseTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);
    // when
    when(bookRepository.findByAuthorEndsWithIgnoreCase(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorEndsWithIgnoreCase(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void findByAuthorNotLikeTest() {
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);
    // when
    when(bookRepository.findByAuthorNotLike(title)).thenReturn(bookList);

    List<Book> result = bookSearchService.findByAuthorNotLike(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void getBooksWithTagTest() {
    List<Long> isbns = Arrays.asList(0l, 1l, 2l);
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);
    when(bookSearchRepository.getBookIsbnsWithTag(title)).thenReturn(isbns);
    when(bookRepository.findAllById(isbns)).thenReturn(bookList);

    List<Book> result = bookSearchService.getBooksWithTag(title);
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }

  @Test
  public void getBooksInTagTest() {
    List<Long> isbns = Arrays.asList(0l, 1l, 2l);
    List<Book> bookList = TestBooksFactory.setupFindAllBooks(null);
    when(bookSearchRepository.getBookIsbnsInTag(Arrays.asList(title))).thenReturn(isbns);
    when(bookRepository.findAllById(isbns)).thenReturn(bookList);

    List<Book> result = bookSearchService.getBooksInTag(Arrays.asList(title));
    assertEquals(3, result.size());
    IntStream.range(0, 3).forEach(i -> TestBooksFactory.assertBook(result.get(i), i));
  }
}
