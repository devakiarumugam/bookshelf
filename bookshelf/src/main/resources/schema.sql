DROP TABLE IF EXISTS BOOK_TAG;

DROP TABLE IF EXISTS BOOK;

CREATE TABLE BOOK (
  ISBN LONG AUTO_INCREMENT  PRIMARY KEY,
  TITLE VARCHAR(250) NOT NULL,
  AUTHOR VARCHAR(250) NOT NULL
);

CREATE TABLE BOOK_TAG (
  BOOK_ISBN LONG NULL,
  TAG VARCHAR(50) NULL,
  PRIMARY KEY(BOOK_ISBN, TAG)
);
