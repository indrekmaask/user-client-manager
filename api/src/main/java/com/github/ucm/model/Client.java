package com.github.ucm.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class Client {

  @Id
  @GeneratedValue
  private UUID id;
  @ManyToOne
  private User user;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String address;

  @ManyToOne
  private Country country;
}
