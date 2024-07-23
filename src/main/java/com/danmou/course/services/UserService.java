package com.danmou.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danmou.course.domain.User;
import com.danmou.course.repository.UserRepo;

@Service
public class UserService {
  
  @Autowired
  private UserRepo repository;
  
  public List<User> findAll() {
    return repository.findAll();
  }
}
