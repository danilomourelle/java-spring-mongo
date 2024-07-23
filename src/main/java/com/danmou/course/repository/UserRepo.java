package com.danmou.course.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.danmou.course.domain.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
}
