package com.bookshelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookshelf.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByTitleStartsWith(String title);

  List<Book> findByTitleLike(String title);

  List<Book> findByTitleEndsWith(String title);

  List<Book> findByTitleStartsWithIgnoreCase(String title);

  List<Book> findByTitleLikeIgnoreCase(String title);

  List<Book> findByTitleEndsWithIgnoreCase(String title);

  List<Book> findByTitleNotLike(String title);

  List<Book> findByAuthorStartsWith(String author);

  List<Book> findByAuthorLike(String author);

  List<Book> findByAuthorEndsWith(String author);

  List<Book> findByAuthorStartsWithIgnoreCase(String author);

  List<Book> findByAuthorLikeIgnoreCase(String author);

  List<Book> findByAuthorEndsWithIgnoreCase(String author);

  List<Book> findByAuthorNotLike(String author);
}
