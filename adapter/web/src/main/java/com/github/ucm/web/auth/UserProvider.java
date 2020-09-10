package com.github.ucm.web.auth;

import com.github.ucm.domain.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProvider implements UserDetailsService {

  private final UserPort userPort;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userPort.findByUsername(username)
      .map(UserPrincipal::of)
      .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
