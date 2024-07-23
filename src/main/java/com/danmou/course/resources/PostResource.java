package com.danmou.course.resources;

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
}
