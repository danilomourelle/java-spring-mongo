package com.danmou.course.dto;

import java.io.Serializable;

import com.danmou.course.domain.User;

public class UserDTO implements Serializable {
  public static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String email;

  public UserDTO() {
  }

  public UserDTO(User original) {
    this.id = original.getId();
    this.name = original.getName();
    this.email = original.getEmail();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
