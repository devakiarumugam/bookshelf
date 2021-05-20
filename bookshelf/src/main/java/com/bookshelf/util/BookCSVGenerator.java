package com.bookshelf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Component;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.BookTags;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BookCSVGenerator {

  public ByteArrayInputStream booksToCsv(List<Book> books) {

    log.info("Start writing books to CSV");
    CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format); ) {

      for (Book book : books) {

        List<String> data = getBook(book);

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      log.info("Completed writing total books to CSV : " + books.size());
      return new ByteArrayInputStream(out.toByteArray());

    } catch (IOException e) {
      log.error("Error Occured while writing books to CSV", e);
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

  public List<String> getBook(Book book) {
    log.info("Start getting books.");
    List<String> tagsStrings = getTagsList(book);

    List<String> data =
        Arrays.asList(
            String.valueOf(book.getIsbn()),
            book.getAuthor(),
            book.getTitle(),
            String.join("~", tagsStrings));
    log.info("Completed getting books.");
    return data;
  }

  public List<String> getTagsList(Book book) {
    log.info("Start getting book Tags.");
    List<BookTags> tags = book.getTags();

    List<String> tagsStrings = new ArrayList<String>();

    for (BookTags tag : tags) {
      tagsStrings.add(tag.getTag());
    }

    log.info("Completed getting book Tags.");
    return tagsStrings;
  }
}
