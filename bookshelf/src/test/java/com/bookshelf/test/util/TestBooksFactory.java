package com.bookshelf.test.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.BookTags;
import com.bookshelf.repository.BookRepository;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestBooksFactory {
  public static final String KENT_BECK = "Kent Beck";
  public static final String TITLE = "Junit testing";
  public static final String TAG1 = "java";
  public static final String TAG2 = "junit";
  public static final String AUTHOR = "Author ";

  public static Book createBook() {
    Book book = new Book();
    book.setAuthor(KENT_BECK);
    book.setTitle(TITLE);
    BookTags bookTag1 = new BookTags();
    bookTag1.setTag(TAG1);
    BookTags bookTag2 = new BookTags();
    bookTag2.setTag(TAG2);
    book.setTags(Arrays.asList(bookTag1, bookTag2));
    return book;
  }

  public static List<Book> setupFindAllBooks(BookRepository bookRepository) {
    List<BookTags> bookTags = getBookTags();
    List<Book> bookList = new ArrayList<Book>();

    IntStream.range(0, 3).forEach(i -> addBook(bookTags, bookList, i));
    // when
    if (bookRepository != null) when(bookRepository.findAll()).thenReturn(bookList);

    return bookList;
  }

  public static void addBook(List<BookTags> bookTags, List<Book> bookList, int index) {
    bookList.add(getBook(bookTags, index));
  }

  public static Book getBook(List<BookTags> bookTags, int index) {
    return new Book(index, TITLE + index, AUTHOR + index, bookTags);
  }

  public static List<BookTags> getBookTags() {
    List<BookTags> bookTags = new ArrayList<>();
    BookTags booktags = new BookTags(1, "tag1");
    bookTags.add(booktags);
    return bookTags;
  }

  public static void assertBook(Book book, int index) {
    assertThat(book.getIsbn()).isEqualTo(index);
    assertThat(book.getAuthor()).isEqualTo(AUTHOR + index);
    assertThat(book.getTitle()).isEqualTo(TITLE + index);
    assertThat(book.getTags().size()).isEqualTo(1);
    assertThat(book.getTags()).isEqualTo(getBookTags());
  }
}
