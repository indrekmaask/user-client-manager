package com.github.ucm.web.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${security.jwt.token.expire-length:3600000}")
  private final long validityInMilliseconds; // 1h
  private final UserDetailsService userDetailsService;
  private String secretKey;

  @PostConstruct
  protected void init() {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
  }

  String createToken(String username) {
    Date now = new Date();
    Date validUntil = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
      .setClaims(Jwts.claims().setSubject(username))
      .setIssuedAt(now)
      .setExpiration(validUntil)
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();
  }

  Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  private String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  Optional<String> resolveToken(HttpServletRequest req) {
    return Optional.ofNullable(req.getHeader("Authorization"))
      .filter(header -> header.startsWith("Bearer "))
      .map(header -> header.substring(7));
  }

  boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}
