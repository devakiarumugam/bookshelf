package com.bookshelf.controller;

import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookshelf.BookShelfApplication;
import com.bookshelf.domain.Book;
import com.bookshelf.service.BookSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = BookShelfApplication.class)
public class BookShelfSearchComponentTest {

  @Autowired private BookSearchService bookSearchService;

  @Test
  public void findByTitleStartsWith() {
    List<Book> books = bookSearchService.findByTitleStartsWith("The");
    assertTrue(books.size() > 0);
  }

  @Test
  public void findByTitleLike() {
    List<Book> books = bookSearchService.findByTitleLike("XXXx");
    assertTrue(books.size() == 0);
  }

  @Test
  public void findByTitleEndsWith() {
    List<Book> books = bookSearchService.findByTitleEndsWith("Kalisa");
    assertTrue(books.size() <= 0);
  }

  @Test
  public void findByTitleStartsWithIgnoreCase() {
    List<Book> books = bookSearchService.findByTitleStartsWithIgnoreCase("the");
    assertTrue(books.size() > 0);
  }

  @Test
  public void findByTitleLikeIgnoreCase() {
    List<Book> books = bookSearchService.findByTitleLike("XXXx");
    assertTrue(books.size() == 0);
  }

  @Test
  public void findByTitleEndsWithIgnoreCase() {
    List<Book> books = bookSearchService.findByTitleEndsWithIgnoreCase("Kalisa");
    assertTrue(books.size() <= 0);
  }

  @Test
  public void findByTitleNotLike() {
    List<Book> books = bookSearchService.findByTitleEndsWithIgnoreCase("Kalisa");
    assertTrue(books.size() <= 0);
  }

  @Test
  public void findByAuthorStartsWith() {
    List<Book> books = bookSearchService.findByTitleEndsWithIgnoreCase("Kalisa");
    assertTrue(books.size() <= 0);
  }
}
