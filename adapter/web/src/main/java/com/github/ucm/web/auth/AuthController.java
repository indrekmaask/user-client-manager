package com.github.ucm.web.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
class AuthController {

  private final AuthenticationManager authManager;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/auth/login")
  ResponseEntity<UserToken> login(@RequestBody UserCredentials credentials) {
    return Optional.of(authManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.username, credentials.password)))
      .map(auth -> (UserPrincipal) auth.getPrincipal())
      .map(UserPrincipal::getUser)
      .map(user -> jwtTokenProvider.createToken(credentials.username))
      .map(token -> ok(UserToken.of(credentials.username, token)))
      .orElseThrow(() -> new BadCredentialsException("Authentication failed"));
  }
}
