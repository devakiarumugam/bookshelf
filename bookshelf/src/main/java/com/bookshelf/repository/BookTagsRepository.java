package com.bookshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookshelf.domain.BookTags;

@Repository
public interface BookTagsRepository extends JpaRepository<BookTags, BookTags> {}
