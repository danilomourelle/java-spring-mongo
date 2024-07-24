package com.danmou.course.repository;

import java.time.Instant;
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

  @Query("{ $and: [ { 'date': { $gte: ?1 } }, { 'date': { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
  List<Post> fullSearch(String text, Instant minDate, Instant maxDate);
}
