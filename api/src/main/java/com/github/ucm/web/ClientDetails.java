package com.github.ucm.web;

import com.github.ucm.model.Client;
import com.github.ucm.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetails {

  private String id;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotBlank
  private String userName;
  @Email
  private String email;
  @NotBlank
  private String address;
  @NotBlank
  private String countryCode;

  public ClientDetails(Client entity) {
    id = entity.id().toString();
    firstName = entity.firstName();
    lastName = entity.lastName();
    userName = entity.username();
    email = entity.email();
    address = entity.address();
    countryCode = Optional.ofNullable(entity.country())
      .map(Country::code)
      .orElse(null);
  }
}
