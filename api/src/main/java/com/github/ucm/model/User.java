package com.github.ucm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class User {

  @Id
  @GeneratedValue
  private UUID id;
  private String username;
  private String password;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<Client> clients = new ArrayList<>();
}
