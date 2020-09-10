package com.github.ucm.web.auth;

import com.nortal.pizzastore.usecase.registeruser.Encoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// TODO! TASK5 password encoding
@Component
class BcryptPasswordEncoder implements Encoder, PasswordEncoder {

  private PasswordEncoder encoder = new BCryptPasswordEncoder(11);

  @Override
  public String encode(CharSequence rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
