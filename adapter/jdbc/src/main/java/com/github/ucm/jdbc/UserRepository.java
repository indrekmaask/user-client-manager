package com.github.ucm.jdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserJdbcEntity, Long> {

  Optional<UserJdbcEntity> findByUsername(String username);

  boolean existsByUsername(String username);
}
