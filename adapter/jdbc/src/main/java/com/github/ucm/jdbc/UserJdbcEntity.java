package com.github.ucm.jdbc;

import com.github.ucm.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Builder
@Table("users")
@AllArgsConstructor
public class UserJdbcEntity {

  @Id
  private Long id;
  private String username;
  private String password;
  private List<ClientJdbcEntity> clients;

  public User toDomainEntity() {
    return User.builder()
      .username(username)
      .password(password)
      .build();
  }

  public static UserJdbcEntity newInstance(User user) {
    return UserJdbcEntity.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .build();
  }
}
