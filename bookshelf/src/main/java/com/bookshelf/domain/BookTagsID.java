package com.bookshelf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BookTagsID implements Serializable {

  private static final long serialVersionUID = 7107100233047549961L;

  private long bookIsbn;

  private String tag;
}
