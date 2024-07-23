package com.danmou.course.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danmou.course.domain.User;
import com.danmou.course.dto.UserDTO;
import com.danmou.course.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

  @Autowired
  private UserService service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> list = service.findAll();
    List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

    return ResponseEntity.ok().body(listDto);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User user = service.findById(id);
    UserDTO userDTO = new UserDTO(user);

    return ResponseEntity.ok().body(userDTO);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
    User user = service.fromDTO(objDto);
    user = service.insert(user);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
  
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
