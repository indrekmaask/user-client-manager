package com.github.ucm.web.auth;

import lombok.Value;

@Value(staticConstructor = "of")
class UserToken {
  public String username;
  public String token;
}
