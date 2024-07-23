package com.danmou.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.danmou.course.domain.Post;
import com.danmou.course.domain.User;
import com.danmou.course.repository.PostRepo;
import com.danmou.course.repository.UserRepo;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepo userRepository;

  @Autowired
  private PostRepo postRepository;

  @Override
  public void run(String... args) throws Exception {
    userRepository.deleteAll();
    postRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    Post post1 = new Post(null, Instant.parse("2024-05-20T20:45:00.000Z"), "Partiu viagem", "Vou viajar para Foz. Abraços!", maria);
    Post post2 = new Post(null, Instant.parse("2001-12-28T13:45:00.000Z"), "Bom dia", "Acordei feliz hoje!", maria);
    
    userRepository.saveAll(Arrays.asList(maria, alex, bob));
    postRepository.saveAll(Arrays.asList(post1, post2));
  }
}
