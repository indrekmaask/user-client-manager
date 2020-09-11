package com.github.ucm.web.auth;

import com.github.ucm.jpa.UserRepository;
import com.github.ucm.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserProvider implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> byUsername = userRepository.findByUsername(username);
    return byUsername
      .map(UserPrincipal::of)
      .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
