package com.danmou.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danmou.course.domain.User;
import com.danmou.course.dto.UserDTO;
import com.danmou.course.repository.UserRepo;
import com.danmou.course.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepo repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = repository.findById(id);

    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  public User insert(User obj) {
    return repository.insert(obj);
  }

  public void delete(String id) {
    findById(id);
    repository.deleteById(id);
  }

  public User update(User obj) {
    User existUser = findById(obj.getId());
    updateData(existUser, obj);

    return repository.save(existUser);
  }

  private void updateData(User existingUser, User newUser) {
    existingUser.setEmail(newUser.getEmail());
    existingUser.setName(newUser.getName());
  }

  public User fromDTO(UserDTO objDto) {
    return new User(
        objDto.getId(),
        objDto.getName(),
        objDto.getEmail());
  }
}
