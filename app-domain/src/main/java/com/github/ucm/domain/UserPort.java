package com.github.ucm.domain;

import java.util.Optional;

public interface UserPort {

  Optional<User> findByUsername(String username);

  void save(User user);

  boolean existsByUsername(String username);
}
