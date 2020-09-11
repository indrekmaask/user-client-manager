package com.github.ucm.web;

import com.github.ucm.model.Client;
import lombok.Value;

@Value
class ClientListItem {

  String id;
  String firstName;
  String lastName;
  String userName;

  public ClientListItem(Client entity) {
    id = entity.id().toString();
    firstName = entity.firstName();
    lastName = entity.lastName();
    userName = entity.username();
  }
}
