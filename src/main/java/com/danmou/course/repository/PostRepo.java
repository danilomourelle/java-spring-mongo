package com.danmou.course.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.danmou.course.domain.Post;

@Repository
public interface PostRepo extends MongoRepository<Post, String> {
  List<Post> findByTitleContainingIgnoreCase(String text);

  @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
  List<Post> findByTitle(String text);
}
