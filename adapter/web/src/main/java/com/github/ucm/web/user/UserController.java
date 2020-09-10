package com.github.ucm.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

  @GetMapping
  public List<String> getAllUsers() {
    return List.of("test1", "test2");
  }
}
