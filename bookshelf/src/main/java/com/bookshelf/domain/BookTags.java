package com.bookshelf.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "book_tag")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@IdClass(BookTagsID.class)
public class BookTags {

  @Id
  @Column(nullable = false, name = "book_isbn", insertable = false, updatable = false)
  private long bookIsbn;

  @Id private String tag;
}
