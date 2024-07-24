package com.danmou.course.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danmou.course.domain.Post;
import com.danmou.course.resources.util.URL;
import com.danmou.course.services.PostService;

@RestController
@RequestMapping(value = "/post")
public class PostResource {

  @Autowired
  private PostService service;

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post post = service.findById(id);

    return ResponseEntity.ok().body(post);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
  public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
    text = URL.decodeParam(text);
    List<Post> list = service.findByTitle(text);

    return ResponseEntity.ok().body(list);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/fullsearch")
  public ResponseEntity<List<Post>> fullSearch(
      @RequestParam(value = "text", defaultValue = "") String text,
      @RequestParam(value = "minDate", defaultValue = "") String minDate,
      @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

    text = URL.decodeParam(text);
    Instant min = URL.startOfDay(minDate, Instant.parse("2000-01-01T00:00:00Z"));
    Instant max = URL.endOfDay(maxDate, Instant.now());

    List<Post> list = service.fullSearch(text, min, max);

    return ResponseEntity.ok().body(list);
  }
}
