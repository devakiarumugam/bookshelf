package com.bookshelf.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.bookshelf.domain.BookTags;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BookSearchRepository {

  private final String BOOK_TAG_QUERY = "SELECT BOOK_ISBN FROM BOOK_TAG WHERE TAG = :tag";

  private final String BOOK_TAG_IN_QUERY = "SELECT BOOK_ISBN FROM BOOK_TAG WHERE TAG IN (%s)";

  private final String BOOK_TAG_DELETE_QUERY = "DELETE FROM BOOK_TAG WHERE BOOK_ISBN = ";

  private final String BOOK_TAG_SELECT_QUERY = "SELECT * FROM BOOK_TAG WHERE BOOK_ISBN = ";

  private final String BOOK_TAG_INSERT_QUERY =
      "INSERT INTO BOOK_TAG ( BOOK_ISBN, TAG) VALUES(?, ?)";

  @Autowired private JdbcTemplate jdbcTemplate;

  @Autowired private NamedParameterJdbcTemplate namedJdbcTemplate;

  public List<Long> getBookIsbnsWithTag(String tag) {
    log.info("Start getting book isbn list with given tag " + tag);
    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("tag", tag);
    List<Long> isbns = namedJdbcTemplate.queryForList(BOOK_TAG_QUERY, namedParameters, Long.class);
    log.info("Completed getting book isbn list with given tag " + tag);
    return isbns;
  }

  public List<Long> getBookIsbnsInTag(List<String> tags) {
    log.info("Start getting book isbn list with given tags" + tags);
    String inSql = String.join(",", Collections.nCopies(tags.size(), "?"));
    List<Long> isbns =
        jdbcTemplate.queryForList(
            String.format(BOOK_TAG_IN_QUERY, inSql), tags.toArray(), Long.class);
    log.info("Completed getting book isbn list with given tags" + tags);
    return isbns;
  }

  public List<BookTags> getBookTagsByIsbn(long isbn) {
    log.info("Start getting BookTags list with given isbn" + isbn);
    List<BookTags> tags =
        jdbcTemplate.query(
            BOOK_TAG_SELECT_QUERY + isbn,
            (rs, rowNum) -> new BookTags(rs.getLong("BOOK_ISBN"), rs.getString("TAG")));
    log.info("Completed getting BookTags list with given isbn" + isbn);
    return tags;
  }

  public int deleteBookTagsWithIsbns(long isbn) {
    log.info("Start deleting BookTags with given isbn " + isbn);
    int result = jdbcTemplate.update(BOOK_TAG_DELETE_QUERY + isbn);
    log.info("Completed deleting BookTags with given isbn " + isbn + " --> Updated = " + result);
    return result;
  }

  public int[][] insertBookTags(List<BookTags> bookTags) {
    log.info("Start inserting BookTags " + bookTags);
    int[][] result =
        jdbcTemplate.batchUpdate(
            BOOK_TAG_INSERT_QUERY,
            bookTags,
            10,
            new ParameterizedPreparedStatementSetter<BookTags>() {
              public void setValues(PreparedStatement ps, BookTags bookTag) throws SQLException {
                ps.setLong(1, bookTag.getBookIsbn());
                ps.setString(2, bookTag.getTag());
              }
            });
    log.info("Completed inserting BookTags " + bookTags);
    return result;
  }
}
