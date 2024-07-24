package com.danmou.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.danmou.course.domain.Post;
import com.danmou.course.domain.User;
import com.danmou.course.dto.AuthorDTO;
import com.danmou.course.dto.CommentDTO;
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
    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post post1 = new Post(
        null,
        Instant.parse("2024-05-20T20:45:00.000Z"),
        "Partiu viagem",
        "Vou viajar para Foz. Abraços!",
        new AuthorDTO(maria));

    Post post2 = new Post(
        null,
        Instant.parse("2024-07-22T12:00:00.000Z"),
        "Bom dia",
        "Acordei feliz hoje!",
        new AuthorDTO(maria));

    CommentDTO c1 = new CommentDTO(
        "Boa viagem mano!",
        Instant.parse("2024-05-20T21:45:00.000Z"),
        new AuthorDTO(alex));

    CommentDTO c2 = new CommentDTO(
        "Aproveite",
        Instant.parse("2024-05-20T22:45:00.000Z"),
        new AuthorDTO(bob));

    CommentDTO c3 = new CommentDTO(
        "Tenha um ótimo dia!",
        Instant.parse("2024-07-22T14:45:00.000Z"),
        new AuthorDTO(alex));

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().addAll(Arrays.asList(c3));

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));
    userRepository.save(maria);
  }
}
