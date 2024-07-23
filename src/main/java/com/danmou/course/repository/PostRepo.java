package com.danmou.course.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.danmou.course.domain.Post;

@Repository
public interface PostRepo extends MongoRepository<Post, String> {
}
