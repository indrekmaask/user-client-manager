package com.github.ucm.web.auth;

import lombok.Value;

@Value
class UserToken {
  String username;
  String token;
}
