package com.danmou.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danmou.course.domain.Post;
import com.danmou.course.repository.PostRepo;
import com.danmou.course.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

  @Autowired
  private PostRepo repository;

  public List<Post> findAll() {
    return repository.findAll();
  }

  public Post findById(String id) {
    Optional<Post> Post = repository.findById(id);

    return Post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  public List<Post> findByTitle(String text) {
    return repository.findByTitleContainingIgnoreCase(text);
  }
}
