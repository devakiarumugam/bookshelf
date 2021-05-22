package com.bookshelf.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @Column(nullable = false)
  private long isbn;

  private @NonNull String title;

  private @NonNull String author;

  @Transient
  private @NonNull List<BookTags> tags;
}
