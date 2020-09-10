package com.github.ucm.jdbc;

import com.github.ucm.domain.User;
import com.github.ucm.domain.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJdbcGateway implements UserPort {

  private final UserRepository repository;

  @Override
  public Optional<User> findByUsername(String username) {
    return repository.findByUsername(username)
      .map(UserJdbcEntity::toDomainEntity);
  }

  @Override
  public void save(User user) {
    UserJdbcEntity entity = UserJdbcEntity.newInstance(user);
    repository.save(entity);
  }

  @Override
  public boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }
}
