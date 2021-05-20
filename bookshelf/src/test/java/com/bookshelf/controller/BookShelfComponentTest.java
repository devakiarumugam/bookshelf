package com.bookshelf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookShelfComponentTest {
  @LocalServerPort private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();

  @SuppressWarnings("rawtypes")
  @Test
  public void testgetAll() throws JsonProcessingException {
    ResponseEntity<List> response =
        restTemplate.getForEntity(createURLWithPort("/api/books"), List.class);
    assertNotNull(response);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Test
  public void testSearchBookByAuthor() {
    ResponseEntity<? extends List> bookGetResponse =
        restTemplate.getForEntity(
            createURLWithPort("/api/bookSearch/findByAuthorStartsWith?search={author}"),
            List.class,
            "Yvonne Vera");
    assertEquals(HttpStatus.OK, bookGetResponse.getStatusCode());
    assertEquals(
        "Opening Spaces: An Anthology of Contemporary African Women Writing",
        ((List<Map<String, String>>) bookGetResponse.getBody()).get(0).get("title"));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void testSearch404() {
    ResponseEntity<? extends List> bookGetResponse =
        restTemplate.getForEntity(
            createURLWithPort("/api/bookSearch/findByAuthorStartsWith?search={author}"),
            List.class,
            "testerror");
    assertEquals(HttpStatus.NOT_FOUND, bookGetResponse.getStatusCode());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
