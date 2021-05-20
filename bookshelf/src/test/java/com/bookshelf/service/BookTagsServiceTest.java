package com.bookshelf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookshelf.domain.BookTags;
import com.bookshelf.repository.BookTagsRepository;
import com.bookshelf.test.util.TestBooksFactory;

@ExtendWith(MockitoExtension.class)
public class BookTagsServiceTest {

  @Mock private BookTagsRepository bookTagsRepository;

  @InjectMocks private BookTagsService bookTagsService;

  private List<BookTags> booksTagsList = TestBooksFactory.getBookTags();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAllBookTagsTest() {
    when(bookTagsRepository.findAll()).thenReturn(booksTagsList);

    List<BookTags> result = bookTagsService.findAllBookTags();
    assertEquals(1, result.size());

    assertEquals(booksTagsList.get(0).getBookIsbn(), result.get(0).getBookIsbn());
    assertEquals(booksTagsList.get(0).getTag(), result.get(0).getTag());
  }

  @Test
  public void findBookTagsByIdTest() {
    when(bookTagsRepository.findById(booksTagsList.get(0)))
        .thenReturn(Optional.of(booksTagsList.get(0)));

    BookTags result = bookTagsService.findBookTagsById(booksTagsList.get(0));

    assertEquals(booksTagsList.get(0).getBookIsbn(), result.getBookIsbn());
    assertEquals(booksTagsList.get(0).getTag(), result.getTag());
  }

  @Test
  public void saveTest() {
    when(bookTagsRepository.save(booksTagsList.get(0))).thenReturn(booksTagsList.get(0));

    BookTags result = bookTagsService.save(booksTagsList.get(0));

    assertEquals(booksTagsList.get(0).getBookIsbn(), result.getBookIsbn());
    assertEquals(booksTagsList.get(0).getTag(), result.getTag());
  }

  @Test
  public void saveAllTest() {
    when(bookTagsRepository.saveAll(booksTagsList)).thenReturn(booksTagsList);

    List<BookTags> result = bookTagsService.saveAll(booksTagsList);
    assertEquals(1, result.size());

    assertEquals(booksTagsList.get(0).getBookIsbn(), result.get(0).getBookIsbn());
    assertEquals(booksTagsList.get(0).getTag(), result.get(0).getTag());
  }

  @Test
  public void deleteTest() {
    doNothing().when(bookTagsRepository).delete(booksTagsList.get(0));

    boolean result = bookTagsService.delete(booksTagsList.get(0));
    assertTrue(result);
  }

  @Test
  public void deleteAllTest() {
    doNothing().when(bookTagsRepository).deleteAll(booksTagsList);

    boolean result = bookTagsService.deleteAll(booksTagsList);
    assertTrue(result);
  }

  @Test
  public void truncateTest() {
    doNothing().when(bookTagsRepository).deleteAll();

    boolean result = bookTagsService.deleteAll();
    assertTrue(result);
  }
}
